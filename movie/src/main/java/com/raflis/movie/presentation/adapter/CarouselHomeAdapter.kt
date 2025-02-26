package com.raflis.movie.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raflis.core.BuildConfig
import com.raflis.core.util.loadImage
import com.raflis.movie.databinding.CarouselHomeItemBinding
import com.raflis.movie.presentation.model.MovieModel
import com.raflis.movie_detail.presentation.screen.MovieDetailActivity

class CarouselHomeAdapter(private val movies: List<MovieModel>) :
    RecyclerView.Adapter<CarouselHomeAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(private val binding: CarouselHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieModel) {
            binding.apply {
                tvTitle.text = movie.title ?: "Untitled"

                ivMoviePoster.loadImage(
                    url = "${BuildConfig.BASE_URL_IMAGE}${movie.backdropPath}"
                )

                root.setOnClickListener {
                    val context = binding.root.context
                    val intent = Intent(context, MovieDetailActivity::class.java).apply {
                        putExtra(MovieDetailActivity.MOVIE_ID, movie.id)
                    }
                    context.startActivity(intent)
                }
            }
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
