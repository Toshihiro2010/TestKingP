package com.example.testking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testking.controler.PhotosAdapter
import com.example.testking.model.DataModel
import com.example.testking.viewModel.MainViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //    private var retrofitClient: Retrofit? = null
    private lateinit var viewModel: MainViewModel
    private lateinit var dataList: MutableList<DataModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Photos"

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.dataList?.observe(this, {
            dataList = it
            Log.d("ddddddddddd => ", Gson().toJson(it))
            updateUI()
        })
        viewModel.getPhotos(1)



    }

    fun updateUI(){
        val photosAdapter = PhotosAdapter(dataList)
        photosAdapter.setItemClickListener(object : PhotosAdapter.itemclickListener {
            override fun onItemClick(item: DataModel) {
                var intent: Intent = Intent(this@MainActivity, PhotoDetailActivity::class.java)
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