package com.raflis.movie.presentation.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raflis.core.BuildConfig
import com.raflis.core.databinding.MovieTopRatedCardItemBinding
import com.raflis.movie.presentation.model.MovieModel
import com.raflis.movie_detail.presentation.screen.MovieDetailActivity


class MovieVerticalAdapter(private val movies: List<MovieModel>) :
    RecyclerView.Adapter<MovieVerticalAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: MovieTopRatedCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieModel) {
            binding.apply {
                tvTitle.text = movie.title ?: "Untitled"
                tvReleaseDate.text = movie.releaseDate ?: "Unknown"
                tvRating.text = movie.voteAverage.toString()

                Glide.with(root.context)
                    .load("${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}")
                    .centerCrop()
                    .into(ivMoviePoster)

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
