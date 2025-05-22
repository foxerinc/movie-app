package com.foxerinc.movieapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foxerinc.movieapp.core.R
import com.foxerinc.movieapp.core.domain.model.Movie
import com.foxerinc.movieapp.core.databinding.ItemListMovieBinding
import java.util.Locale

class MovieAdapter: ListAdapter<Movie, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            with(binding) {
                tvTitle.text = data.movieName
                tvReleaseDate.text = data.releaseDate
                tvRating.text = String.format(Locale.getDefault(), "%.1f", data.voteAverage)

                Glide.with(root.context)
                    .load(data.posterUrl)
                    .placeholder(R.drawable.sample_image)
                    .into(imgPoster)

                root.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}