package com.example.testking.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testking.viewModel.MainViewModel
import com.example.testking.R
import com.example.testking.controler.PhotosAdapter
import com.example.testking.model.DataModel
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

        viewModel.dataList?.observe(viewLifecycleOwner) {
            photosAdapter = PhotosAdapter(it).apply {
                setItemClickListener(object : PhotosAdapter.ItemClickListener {
                    override fun onItemClick(item: DataModel) {
                        val bundle: Bundle = Bundle()
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
        }
        viewModel.getPhotos(1)
    }


}