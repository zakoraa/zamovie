package com.raflis.movie.presentation

import android.os.Bundle
import com.raflis.core.databinding.ActivityBaseScreenBinding
import com.raflis.core.presentation.BaseScreenActivity
import com.raflis.movie.databinding.ActivityHomeBinding

class HomeActivity : BaseScreenActivity() {

    private lateinit var baseBinding: ActivityBaseScreenBinding
    private lateinit var homeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseBinding = ActivityBaseScreenBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)

        homeBinding = ActivityHomeBinding.inflate(layoutInflater)

        baseBinding.container.addView(homeBinding.root)
    }
}
