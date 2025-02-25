package com.raflis.movie_detail.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.raflis.core.BuildConfig
import com.raflis.core.util.DateFormatter.extractYear
import com.raflis.core.util.Resource
import com.raflis.movie_detail.R
import com.raflis.movie_detail.databinding.ActivityMovieDetailBinding
import com.raflis.movie_detail.presentation.view_model.MovieDetailViewModel
import com.raflis.movie_detail.util.MovieDetailDataMapper.mapDomainToPresentation
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

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
        initAction()
    }

    private fun initView() {
        val movieId = intent.getIntExtra(MOVIE_ID, -1)

        if (movieId != -1) {
            handleGetMovieDetailById(movieId)
        }
    }

    private fun initAction() {
        binding.apply {
            ivBack.setOnClickListener {
                finish()
            }
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
                            val movie = mapDomainToPresentation(movieDetail.data)
                            tvTitle.text =
                                String.format(
                                    Locale.US,
                                    "${movie.title} (${extractYear(movie.releaseDate)})"
                                )
                            tvDesc.text = movie.overview
                            tvRating.text = String.format(Locale.US, movie.voteAverage.toString())
                            Glide.with(root.context)
                                .load("${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}")
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
        const val MOVIE_ID = "movie_id"
    }
}