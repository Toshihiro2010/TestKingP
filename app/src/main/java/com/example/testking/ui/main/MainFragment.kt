package com.example.testking.ui.main

import android.os.Bundle
import android.os.Parcel
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testking.viewModel.MainViewModel
import com.example.testking.R
import com.example.testking.controler.PhotosAdapter
import com.example.testking.model.DataModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.main_fragment.view.recyclerPhotos

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var photosAdapter: PhotosAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val gridLayoutManager = GridLayoutManager(context, 2)

        viewModel.dataList?.observe(viewLifecycleOwner, {
            Log.d("ddddddddddd => ", Gson().toJson(it))
            photosAdapter = PhotosAdapter(it).apply {
                setItemClickListener(object : PhotosAdapter.itemclickListener {
                    override fun onItemClick(item: DataModel) {
                        var bundle: Bundle = Bundle()
                        bundle.putInt("num", 1)
                        bundle.putParcelable("dataObject", item)
                        findNavController().navigate(
                            R.id.action_mainFragment_to_photoDetail,
                            bundle
                        )
                    }
                })
            }

            view.recyclerPhotos.apply {
                adapter = photosAdapter
                layoutManager = gridLayoutManager
            }
        })
        viewModel.getPhotos(1)
    }


}