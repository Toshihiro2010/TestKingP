package com.example.testking.service

import com.example.testking.model.DataModel
import io.reactivex.rxjava3.core.Observable

import retrofit2.http.GET
import retrofit2.http.Path


interface AlbumsService {

    @GET("albums/{albumId}/photos")
    fun getPhotos(@Path("albumId") albumId: Int): Observable<MutableList<DataModel>>


}