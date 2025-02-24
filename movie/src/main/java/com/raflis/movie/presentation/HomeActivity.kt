package com.raflis.movie.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.raflis.core.databinding.ActivityBaseScreenBinding
import com.raflis.core.presentation.BaseScreenActivity
import com.raflis.core.util.Resource
import com.raflis.movie.databinding.ActivityHomeBinding
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

    private fun initView(){
        handleGetMoviesForYou()
    }

    private fun handleGetMoviesForYou() {
        homeViewModel.moviesForYou.observe(this) { moviesForYou ->
            if (moviesForYou != null) {
                homeBinding.apply {
                    when (moviesForYou) {
                        is Resource.Loading -> showMoviesForYouLoading(true)

                        is Resource.Success -> {
                            showMoviesForYouLoading(false)
                            Log.d("FLORAAAAA", "handleGetMoviesForYou: ${moviesForYou.data}")
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
