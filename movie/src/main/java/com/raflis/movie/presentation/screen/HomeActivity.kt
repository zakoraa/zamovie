package com.raflis.movie.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raflis.core.databinding.ActivityBaseScreenBinding
import com.raflis.core.presentation.BaseScreenActivity
import com.raflis.core.util.Resource
import com.raflis.movie.databinding.ActivityHomeBinding
import com.raflis.movie.presentation.adapter.CarouselHomeAdapter
import com.raflis.movie.presentation.adapter.MovieHorizontalAdapter
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
    }

    private fun initView() {
        handleGetMoviesForYou()
    }

    private fun handleGetMoviesForYou() {
        homeViewModel.moviesForYou.observe(this) { moviesForYou ->
            if (moviesForYou != null) {
                homeBinding.apply {
                    when (moviesForYou) {
                        is Resource.Loading -> {
                            tvForYou.visibility = View.VISIBLE
                            showMoviesForYouLoading(true)
                        }

                        is Resource.Success -> {
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

                        is Resource.Error -> {
                            showMoviesForYouLoading(false)
                            tvForYou.visibility = View.GONE
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
}
