package com.raflis.core.presentation.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raflis.core.databinding.ActivityBaseScreenBinding

open class BaseScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
