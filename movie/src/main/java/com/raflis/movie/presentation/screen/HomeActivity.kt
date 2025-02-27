package com.raflis.movie.presentation.screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.raflis.core.databinding.ActivityBaseScreenBinding
import com.raflis.core.presentation.screen.BaseScreenActivity
import com.raflis.core.util.ResourceState
import com.raflis.core.util.ToastUtil
import com.raflis.movie.databinding.ActivityHomeBinding
import com.raflis.movie.presentation.adapter.CarouselHomeAdapter
import com.raflis.movie.presentation.adapter.MovieHorizontalAdapter
import com.raflis.movie.presentation.adapter.MovieVerticalAdapter
import com.raflis.movie.presentation.view_model.HomeViewModel
import com.raflis.movie.util.MovieDataMapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseScreenActivity() {

    private lateinit var baseBinding: ActivityBaseScreenBinding
    private lateinit var homeBinding: ActivityHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseBinding = ActivityBaseScreenBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)

        homeBinding = ActivityHomeBinding.inflate(layoutInflater)

        baseBinding.container.addView(homeBinding.root)
        initView()
        initAction()
    }

    private fun initView() {
        handleGetMoviesForYou()
        handleGetMoviesPopular()
        handleGetMoviesTopRated()
    }

    private fun initAction() {
        homeBinding.apply {
            ivFavorite.setOnClickListener {
                try {
                    installFavoriteMovieModule()
                } catch (e: Exception) {
                    ToastUtil.showToast(this@HomeActivity, "Module not found")
                }
            }
        }
    }

    private fun installFavoriteMovieModule() {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val moduleFavoriteMovie = "favorite_movie"
        if (splitInstallManager.installedModules.contains(moduleFavoriteMovie)) {
            moveToFavoriteMovieActivity()
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleFavoriteMovie)
                .build()
            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    ToastUtil.showToast(
                        this@HomeActivity,
                        getString(com.raflis.core.R.string.install_module_success_message)
                    )
                    moveToFavoriteMovieActivity()
                }
                .addOnFailureListener {
                    ToastUtil.showToast(
                        this@HomeActivity,
                        getString(com.raflis.core.R.string.install_module_failed_message)
                    )
                }
        }
    }

    private fun moveToFavoriteMovieActivity() {
        startActivity(
            Intent(
                this,
                Class.forName("com.raflis.zamovie.favorite_movie.presentation.screen.FavoriteMovieActivity")
            )
        )
    }

    private fun handleGetMoviesForYou() {
        homeViewModel.moviesForYou.observe(this) { moviesForYou ->
            if (moviesForYou != null) {
                homeBinding.apply {
                    when (moviesForYou) {
                        is ResourceState.Loading -> {
                            tvForYou.visibility = View.VISIBLE
                            showMoviesForYouLoading(true)
                        }

                        is ResourceState.Success -> {
                            showMoviesForYouLoading(false)
                            tvForYou.visibility = View.VISIBLE

                            val movieModelList = MovieDataMapper.mapDomainListToPresentation(
                                moviesForYou.data ?: emptyList()
                            )

                            if (movieModelList.isEmpty()) {
                                tvForYou.visibility = View.GONE
                                rvForYou.visibility = View.GONE
                                rvCarouselHome.visibility = View.GONE
                            } else {
                                tvForYou.visibility = View.VISIBLE
                                rvForYou.visibility = View.VISIBLE
                                rvCarouselHome.visibility = View.VISIBLE
                            }

                            val adapter = MovieHorizontalAdapter(movieModelList)
                            rvForYou.layoutManager = LinearLayoutManager(
                                this@HomeActivity,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            rvForYou.adapter = adapter

                            val carouselAdapter =
                                CarouselHomeAdapter(movieModelList.shuffled().take(3))
                            rvCarouselHome.layoutManager = LinearLayoutManager(
                                this@HomeActivity,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            rvCarouselHome.adapter = carouselAdapter
                        }

                        is ResourceState.Error -> {
                            showMoviesForYouLoading(false)
                            tvForYou.visibility = View.GONE
                            ToastUtil.showToast(
                                this@HomeActivity,
                                getString(com.raflis.core.R.string.get_movie_failed_message)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun showMoviesForYouLoading(isLoading: Boolean) {
        homeBinding.apply {
            if (isLoading) {
                progressForYou.visibility = View.VISIBLE
            } else {
                progressForYou.visibility = View.GONE
            }
        }
    }

    private fun handleGetMoviesPopular() {
        homeViewModel.moviesPopular.observe(this) { moviesPopular ->
            if (moviesPopular != null) {
                homeBinding.apply {
                    when (moviesPopular) {
                        is ResourceState.Loading -> {
                            tvPopular.visibility = View.VISIBLE
                            showMoviesPopularLoading(true)
                        }

                        is ResourceState.Success -> {
                            showMoviesPopularLoading(false)
                            tvPopular.visibility = View.VISIBLE
                            val data  = moviesPopular.data
                            val movieModelList = MovieDataMapper.mapDomainListToPresentation(
                                data ?: emptyList()
                            )

                            if (movieModelList.isEmpty()) {
                                tvPopular.visibility = View.GONE
                                rvPopular.visibility = View.GONE
                            } else {
                                tvPopular.visibility = View.VISIBLE
                                rvPopular.visibility = View.VISIBLE
                            }

                            val adapter = MovieHorizontalAdapter(movieModelList)
                            rvPopular.layoutManager = LinearLayoutManager(
                                this@HomeActivity,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            rvPopular.adapter = adapter
                        }

                        is ResourceState.Error -> {
                            showMoviesPopularLoading(false)
                            tvPopular.visibility = View.GONE
                            ToastUtil.showToast(
                                this@HomeActivity,
                                getString(com.raflis.core.R.string.get_movie_failed_message)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun showMoviesPopularLoading(isLoading: Boolean) {
        homeBinding.apply {
            if (isLoading) {
                progressPopular.visibility = View.VISIBLE
            } else {
                progressPopular.visibility = View.GONE
            }
        }
    }

    private fun handleGetMoviesTopRated() {
        homeViewModel.moviesTopRated.observe(this) { moviesTopRated ->
            if (moviesTopRated != null) {
                homeBinding.apply {
                    when (moviesTopRated) {
                        is ResourceState.Loading -> {
                            tvTopRated.visibility = View.VISIBLE
                            showMoviesTopRatedLoading(true)
                        }

                        is ResourceState.Success -> {
                            showMoviesTopRatedLoading(false)
                            tvTopRated.visibility = View.VISIBLE

                            val movieModelList = MovieDataMapper.mapDomainListToPresentation(
                                moviesTopRated.data ?: emptyList()
                            )

                            if (movieModelList.isEmpty()) {
                                tvTopRated.visibility = View.GONE
                                rvTopRated.visibility = View.GONE
                            } else {
                                tvTopRated.visibility = View.VISIBLE
                                rvTopRated.visibility = View.VISIBLE
                            }

                            val adapter = MovieVerticalAdapter(movieModelList)
                            rvTopRated.layoutManager = LinearLayoutManager(
                                this@HomeActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            rvTopRated.adapter = adapter
                        }

                        is ResourceState.Error -> {
                            showMoviesTopRatedLoading(false)
                            tvTopRated.visibility = View.GONE
                            ToastUtil.showToast(
                                this@HomeActivity,
                                getString(com.raflis.core.R.string.get_movie_failed_message)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun showMoviesTopRatedLoading(isLoading: Boolean) {
        homeBinding.apply {
            if (isLoading) {
                progressTopRated.visibility = View.VISIBLE
            } else {
                progressTopRated.visibility = View.GONE
            }
        }
    }
}
