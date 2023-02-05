package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.Result
import com.example.movieapp.databinding.GridViewItemBinding

class PhotoGridAdapter(val onClickListener: OnClickListener) : ListAdapter<Result,
        PhotoGridAdapter.MoviePhotoViewHolder>(DiffCallback) {


    class MoviePhotoViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(MoviePhoto: Result) {
            binding.result = MoviePhoto
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.backdropPath == newItem.backdropPath
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.MoviePhotoViewHolder {
        return MoviePhotoViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MoviePhotoViewHolder, position: Int) {
        val moviePhoto = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(moviePhoto)
        }
        holder.bind(moviePhoto)
    }

//    class OnClickListener(val clickListener: (movieProperty:Result) -> Unit) {
//        fun onClick(movieProperty:Result) = clickListener(movieProperty)
//    }
    class OnClickListener(val clickListener: (movieProparty: Result)->Unit){
        fun onClick(movieProparty: Result) = clickListener(movieProparty)
    }

}