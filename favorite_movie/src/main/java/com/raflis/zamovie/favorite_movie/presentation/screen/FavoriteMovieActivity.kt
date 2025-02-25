package com.raflis.zamovie.favorite_movie.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raflis.core.databinding.ActivityBaseScreenBinding
import com.raflis.core.presentation.screen.BaseScreenActivity
import com.raflis.core.presentation.view_model.FavoriteMovieViewModelFactory
import com.raflis.core.presentation.view_model.FavoriteViewModel
import com.raflis.core.util.FavoriteMovieDataMapper.mapDomainsToPresentation
import com.raflis.zamovie.favorite_movie.databinding.ActivityFavoriteMovieBinding
import com.raflis.zamovie.favorite_movie.presentation.adapter.FavoriteMovieAdapter

class FavoriteMovieActivity : BaseScreenActivity() {
    private lateinit var baseBinding: ActivityBaseScreenBinding
    private lateinit var binding: ActivityFavoriteMovieBinding

    private val viewModel: FavoriteViewModel by viewModels { FavoriteMovieViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseBinding = ActivityBaseScreenBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)

        binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)

        baseBinding.container.addView(binding.root)
        initView()
        initAction()
    }

    private fun initView() {
        handleGetAllFavoriteMovies()
    }

    private fun initAction() {
        binding.apply {
            ivBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun handleGetAllFavoriteMovies() {
        binding.apply {
            viewModel.favoriteMovies.observe(this@FavoriteMovieActivity) {
                if (it.isEmpty()) {
                    tvNoData.visibility = View.VISIBLE
                    rvFavoriteMovie.visibility = View.GONE
                } else {
                    tvNoData.visibility = View.GONE
                    rvFavoriteMovie.visibility = View.VISIBLE

                    val favoriteMovieList = mapDomainsToPresentation(it)
                    val adapter = FavoriteMovieAdapter(favoriteMovieList)
                    rvFavoriteMovie.layoutManager = LinearLayoutManager(
                        this@FavoriteMovieActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    rvFavoriteMovie.adapter = adapter
                }
            }
        }
    }
}