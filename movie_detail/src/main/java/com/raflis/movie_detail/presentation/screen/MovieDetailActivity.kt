package com.raflis.movie_detail.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.raflis.core.BuildConfig
import com.raflis.core.domain.model.FavoriteMovie
import com.raflis.core.presentation.view_model.FavoriteMovieViewModelFactory
import com.raflis.core.presentation.view_model.FavoriteViewModel
import com.raflis.core.util.DateFormatter.extractYear
import com.raflis.core.util.ResourceState
import com.raflis.core.util.ToastUtil
import com.raflis.core.util.loadImage
import com.raflis.movie_detail.R
import com.raflis.movie_detail.databinding.ActivityMovieDetailBinding
import com.raflis.movie_detail.presentation.model.MovieDetailModel
import com.raflis.movie_detail.presentation.view_model.MovieDetailViewModel
import com.raflis.movie_detail.util.MovieDetailDataMapper.mapDomainToPresentation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModels()
    private val favoriteMovieViewModel: FavoriteViewModel by viewModels {
        FavoriteMovieViewModelFactory(
            this
        )
    }

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
            viewModel.fetchMovieDetail(movieId)
            observeMovieDetail()
            observeFavoriteStatus(movieId)
        }
    }

    private fun initAction() {
        binding.apply {
            ivBack.setOnClickListener {
                finish()
            }
            ivFavorite.setOnClickListener {
                val movieDetail = viewModel.movieDetail.value
                if (movieDetail is ResourceState.Success) {
                    handleToggleFavoriteMovie(mapDomainToPresentation(movieDetail.data))
                }
            }
        }
    }

    private fun observeFavoriteStatus(movieId: Int) {
        favoriteMovieViewModel.isFavoriteMovie(movieId).observe(this) { favoriteMovie ->
            binding.apply {
                if (favoriteMovie.id != null) {
                    ivFavorite.setImageResource(com.raflis.core.R.drawable.ic_favorite)
                } else {
                    ivFavorite.setImageResource(com.raflis.core.R.drawable.ic_favorite_outline)
                }
            }
        }
    }

    private fun handleToggleFavoriteMovie(movieDetailModel: MovieDetailModel) {
        val favoriteMovie = FavoriteMovie(
            title = movieDetailModel.title,
            posterPath = movieDetailModel.posterPath,
            releaseDate = movieDetailModel.releaseDate,
            voteAverage = movieDetailModel.voteAverage,
            id = movieDetailModel.id
        )
        lifecycleScope.launch {
            favoriteMovieViewModel.toggleFavoriteMovie(favoriteMovie)
            observeFavoriteStatus(favoriteMovie.id ?: 0)
        }

    }

    private fun observeMovieDetail() {
        lifecycleScope.launch {
            viewModel.movieDetail.collect { movieDetail ->
                binding.apply {
                    when (movieDetail) {
                        is ResourceState.Loading -> {
                            showMoviesForYouLoading(true)
                            ivFavorite.isEnabled = false
                        }

                        is ResourceState.Success -> {
                            showMoviesForYouLoading(false)
                            val movie = mapDomainToPresentation(movieDetail.data)
                            ivFavorite.isEnabled = true

                            tvTitle.text =
                                String.format(
                                    Locale.US,
                                    "${movie.title} (${extractYear(movie.releaseDate)})"
                                )
                            tvDesc.text = movie.overview
                            tvRating.text = String.format(Locale.US, movie.voteAverage.toString())

                            ivMoviePoster.loadImage(
                                url = "${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}"
                            )

                            ivFavorite.setOnClickListener {
                                handleToggleFavoriteMovie(movie)
                            }
                        }

                        is ResourceState.Error -> {
                            showMoviesForYouLoading(false)
                            ivFavorite.isEnabled = false
                            ToastUtil.showToast(
                                this@MovieDetailActivity,
                                getString(com.raflis.core.R.string.get_movie_failed_message)
                            )
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