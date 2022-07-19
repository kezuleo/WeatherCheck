package com.example.weathercheck.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Transformations.map
import com.example.weathercheck.R
import com.example.weathercheck.data.model.WeatherItem
import com.example.weathercheck.data.net.ApiFactory
import com.example.weathercheck.databinding.ActivityMainBinding
import com.example.weathercheck.domain.WeatherItemMapper
import com.example.weathercheck.presentation.fragments.MainFragment
import kotlinx.coroutines.*
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder, MainFragment.newInstance())
            .commit()
    }
}