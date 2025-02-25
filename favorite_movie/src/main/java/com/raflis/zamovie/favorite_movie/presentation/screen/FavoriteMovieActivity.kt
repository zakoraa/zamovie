package com.raflis.zamovie.favorite_movie.presentation.screen

import android.os.Bundle
import com.raflis.core.databinding.ActivityBaseScreenBinding
import com.raflis.core.presentation.screen.BaseScreenActivity
import com.raflis.zamovie.favorite_movie.databinding.ActivityFavoriteMovieBinding

class FavoriteMovieActivity : BaseScreenActivity() {
    private lateinit var baseBinding: ActivityBaseScreenBinding
    private lateinit var binding: ActivityFavoriteMovieBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseBinding = ActivityBaseScreenBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)

        binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)

        baseBinding.container.addView(binding.root)

    }
}