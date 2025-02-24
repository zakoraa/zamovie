package com.raflis.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raflis.core.BuildConfig
import com.raflis.movie.databinding.MovieCardItemBinding
import com.raflis.movie.presentation.model.MovieModel

class MovieHorizontalAdapter(private val movies: List<MovieModel>) :
    RecyclerView.Adapter<MovieHorizontalAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: MovieCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel) {
            binding.apply {
                tvTitle.text = movie.title ?: "Untitled"
                tvReleaseDate.text = movie.releaseDate ?: "Unknown Release Date"

                Glide.with(root.context)
                    .load("${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}")
                    .centerCrop()
                    .into(ivMoviePoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieCardItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}
