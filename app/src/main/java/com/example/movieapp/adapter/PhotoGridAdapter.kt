package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.Result
import com.example.movieapp.databinding.GridViewItemBinding

/*
 * This class implements a RecyclerView, ListAdapter which uses Data Binding to present List
 * data, including computing diffs between lists.
 * @param onClick a lambda that takes the
 */

class PhotoGridAdapter(val onClickListener: OnClickListener) : ListAdapter<Result,
        PhotoGridAdapter.MoviePhotoViewHolder>(DiffCallback) {

    /*
     * The MarsPropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full MovieProperty information.
     */
    class MoviePhotoViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(MoviePhoto: Result) {
            binding.result = MoviePhoto
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }
    /*
     * Allows the RecyclerView to determine which items have changed when the List of MovieProperty
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.backdropPath == newItem.backdropPath
        }
    }

    /*
     * Create new RecyclerView item views invoked by the layout manager
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.MoviePhotoViewHolder {
        return MoviePhotoViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /*
     * Replaces the contents of a view invoked by the layout manager
     */
    override fun onBindViewHolder(holder: PhotoGridAdapter.MoviePhotoViewHolder, position: Int) {
        val moviePhoto = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(moviePhoto)
        }
        holder.bind(moviePhoto)
    }



    /*
     * Custom listener that handles clicks on RecyclerView items.  Passes the MovieProperty
     * associated with the current item to the onClick function.
     * @param clickListener lambda that will be called with the current MovieProperty
     */
    class OnClickListener(val clickListener: (movieProparty: Result)->Unit){
        fun onClick(movieProparty: Result) = clickListener(movieProparty)
    }

}