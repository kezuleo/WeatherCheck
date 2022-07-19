package com.example.weathercheck.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathercheck.R
import com.example.weathercheck.data.net.ApiFactory
import com.example.weathercheck.databinding.FragmentMainBinding
import com.example.weathercheck.domain.WeatherItemMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class MainFragment : Fragment() {

    private val WIMapper = WeatherItemMapper()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}