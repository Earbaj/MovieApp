package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.Result
import com.example.movieapp.databinding.GridViewItemBinding

class PhotoGridAdapter : ListAdapter<Result,
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
        holder.bind(moviePhoto)
    }


}