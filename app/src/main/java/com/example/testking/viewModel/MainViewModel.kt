package com.example.testking.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testking.model.DataModel
import com.example.testking.service.AlbumsService
import com.example.testking.service.RetrofitClient
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit

class MainViewModel : ViewModel() {

    var dataList: MutableLiveData<MutableList<DataModel>>? = MutableLiveData()

    var currentId: Int? = null


    private var retrofitClient: Retrofit? = null
    private var albumsService: AlbumsService? = null


    init {
        retrofitClient = RetrofitClient().getClient()
        albumsService = retrofitClient?.create(AlbumsService::class.java)
    }


    fun getPhotos(id: Int): LiveData<MutableList<DataModel>>? {
        if (currentId != id) {
            currentId = id
            albumsService!!.getPhotos(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("getPhotos => ", Gson().toJson(it))
                    dataList?.value = it
                }
        }
        return dataList as MutableLiveData
    }


}