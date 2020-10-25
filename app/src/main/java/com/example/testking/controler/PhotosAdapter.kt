package com.example.testking.controler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testking.R
import com.example.testking.model.DataModel
import kotlinx.android.synthetic.main.item_photo.view.*


class PhotosAdapter(var items: MutableList<DataModel>) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {


    private var photosListener: itemclickListener? = null
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(items, position)

        holder.itemView.setOnClickListener {
            photosListener?.onItemClick(items[position])
        }
        
    }

    override fun getItemCount(): Int = items.size


    interface itemclickListener{
        fun onItemClick(item: DataModel)
    }

    fun setItemClickListener(listener : itemclickListener){
        photosListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        fun bind(items: List<DataModel>, position: Int){
//            val pathImg = "https://pbs.twimg.com/media/D-zgo77UIAUC__9.jpg"
            val currentItem = items[position]
            val pathImg = currentItem.thumbnailUrl
            val title = currentItem.title

            itemView.apply {
                Glide.with(context)
                    .load(pathImg)
                    .centerCrop()
                    .error(R.drawable.no_image)
                    .placeholder(R.drawable.ic_android_white_24dp)
                    .into(imgItem)
                txtTitleItem.text = title
            }


        }
    }

}