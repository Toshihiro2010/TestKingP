package com.example.testking.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testking.R
import com.example.testking.model.DataModel
import com.google.gson.Gson


class PhotoDetailFragment : Fragment() {

    private var photoDetail: DataModel? = null
    private var strDetail: String? = "test"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var numGet = arguments?.getInt("num")
        strDetail = "GETNUM : $numGet"
        val temp = arguments?.getParcelable<DataModel>("dataObject")
        Log.d("numGet => ", "get : $numGet")
        Log.d("temp => ", "get : ${Gson().toJson(temp)}")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}





