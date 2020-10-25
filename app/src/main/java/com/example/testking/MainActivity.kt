package com.example.testking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testking.controler.PhotosAdapter
import com.example.testking.model.DataModel
import com.example.testking.viewModel.MainViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var dataList: MutableList<DataModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.photoTitle)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.dataList?.observe(this) {
            dataList = it
            Log.d("Data => ", Gson().toJson(it))
            updateUI()
        }
        viewModel.getPhotos(1)



    }

    fun updateUI(){
        val photosAdapter = PhotosAdapter(dataList)
        photosAdapter.setItemClickListener(object : PhotosAdapter.ItemClickListener {
            override fun onItemClick(item: DataModel) {
                val intent: Intent = Intent(this@MainActivity, PhotoDetailActivity::class.java)
                intent.let {
                    it.putExtra("dataObject", item)
                }
                startActivity(intent)

            }
        })
        val gridLayoutManager = GridLayoutManager(this, 2)


        recyclerPhotos.apply {
            adapter = photosAdapter
            layoutManager = gridLayoutManager
        }
    }
}