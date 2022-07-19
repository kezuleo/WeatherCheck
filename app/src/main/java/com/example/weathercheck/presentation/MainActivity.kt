package com.example.weathercheck.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Transformations.map
import com.example.weathercheck.data.model.WeatherItem
import com.example.weathercheck.data.net.ApiFactory
import com.example.weathercheck.databinding.ActivityMainBinding
import com.example.weathercheck.domain.WeatherItemMapper
import kotlinx.coroutines.*
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val WIMapper = WeatherItemMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bGetData.setOnClickListener {
            getDataNow()
        }
    }

    private fun getDataNow() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ApiFactory.apiService.fetchData().awaitResponse()
            val data = WIMapper.transfer(result.body()!!)//TODO("убрать !! и поменять их на проверку")
            Log.d("MyTag", data.toString())
            withContext(Dispatchers.Main) {
                binding.tvTempData.text = data.temp.toString()
                binding.tvFeelsLikeData.text = data.feels_like.toString()
                binding.tvPressureData.text = data.pressure.toString()
            }
        }
    }
}