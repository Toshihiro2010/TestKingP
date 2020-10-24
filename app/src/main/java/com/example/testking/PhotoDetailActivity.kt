package com.example.testking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.testking.model.DataModel
import kotlinx.android.synthetic.main.activity_photo_detail.*

class PhotoDetailActivity : AppCompatActivity() {

    var photoDetail: DataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowCustomEnabled(true)
        }

        var bundle = intent.extras
        photoDetail = bundle?.getParcelable("dataObject")
        title = photoDetail?.title

        Glide.with(this)
            .load(photoDetail?.url)
            .centerCrop()
            .skipMemoryCache(false)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.no_image)
            .into(imgDetail)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}