package com.raflis.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raflis.core.BuildConfig
import com.raflis.movie.databinding.CarouselHomeItemBinding
import com.raflis.core.presentation.model.MovieModel

class CarouselHomeAdapter(private val movies: List<MovieModel>) :
    RecyclerView.Adapter<CarouselHomeAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(private val binding: CarouselHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel) {
            binding.tvTitle.text = movie.title ?: "Untitled"

            Glide.with(binding.root.context)
                .load("${BuildConfig.BASE_URL_IMAGE}${movie.backdropPath}")
                .centerCrop()
                .into(binding.ivMoviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = CarouselHomeItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}
