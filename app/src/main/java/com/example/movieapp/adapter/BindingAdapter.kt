package com.example.movieapp.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.data.Result


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val BASE_URL = "https://image.tmdb.org/t/p/w500" + imgUrl
        //val imgUri = imgUrl.toUri().buildUpon().scheme("https://image.tmdb.org/t/p/w500").build()
        val imgUri = BASE_URL.toUri().buildUpon().build()
        imgView.load(imgUri)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Result>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)

}
