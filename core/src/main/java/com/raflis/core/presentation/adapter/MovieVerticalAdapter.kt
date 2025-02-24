package com.raflis.core.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raflis.core.BuildConfig
import com.raflis.core.databinding.MovieTopRatedCardItemBinding
import com.raflis.core.presentation.model.MovieModel


class MovieVerticalAdapter(private val movies: List<MovieModel>) :
    RecyclerView.Adapter<MovieVerticalAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: MovieTopRatedCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieModel) {
            binding.tvTitle.text = movie.title ?: "Untitled"
            binding.tvReleaseDate.text = movie.releaseDate ?: "Unknown"
            binding.tvRating.text = movie.voteAverage.toString()

            Glide.with(binding.root.context)
                .load("${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}")
                .centerCrop()
                .into(binding.ivMoviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieTopRatedCardItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}
