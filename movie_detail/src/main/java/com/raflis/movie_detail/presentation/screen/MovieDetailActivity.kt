package com.raflis.movie_detail.presentation.screen

import android.content.Intent.EXTRA_USER
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.raflis.core.BuildConfig
import com.raflis.core.presentation.model.MovieModel
import com.raflis.core.util.DateFormatter.extractYear
import com.raflis.core.util.Resource
import com.raflis.movie_detail.R
import com.raflis.movie_detail.databinding.ActivityMovieDetailBinding
import com.raflis.movie_detail.presentation.view_model.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val movieModel: MovieModel? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_USER,
                MovieModel::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_MOVIE)
        }

        if (movieModel != null) {
            handleGetMovieDetailById(movieModel.id ?: 0)
        }
    }

    private fun handleGetMovieDetailById(id: Int) {
        movieDetailViewModel.getMovieDetailById(id).observe(this) { movieDetail ->
            if (movieDetail != null) {
                binding.apply {
                    when (movieDetail) {
                        is Resource.Loading ->
                            showMoviesForYouLoading(true)

                        is Resource.Success -> {
                            showMoviesForYouLoading(false)
                            val movie = movieDetail.data
                            tvTitle.text =
                                "${movie?.title} (${extractYear(movie?.releaseDate)})" ?: ""
                            tvDesc.text = movie?.overview ?: ""
                            tvRating.text = movie?.voteAverage.toString()
                            Glide.with(root.context)
                                .load("${BuildConfig.BASE_URL_IMAGE}${movie?.posterPath}")
                                .centerCrop()
                                .into(ivMoviePoster)
                        }

                        is Resource.Error -> {
                            showMoviesForYouLoading(false)
                        }
                    }
                }
            }
        }
    }

    private fun showMoviesForYouLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                constraintBoxMovie.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            } else {
                constraintBoxMovie.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}