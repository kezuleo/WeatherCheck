package com.example.weathercheck.presentation.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.weathercheck.R
import com.example.weathercheck.data.net.ApiFactory
import com.example.weathercheck.databinding.FragmentMainBinding
import com.example.weathercheck.domain.WeatherItemMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.lang.NullPointerException

class MainFragment : Fragment() {

    private lateinit var pLauncher: ActivityResultLauncher<String>

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private val weatherItemMapper = WeatherItemMapper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        binding.bUpdateWeather.setOnClickListener {
            getDataNow()
        }
    }

    private fun getDataNow() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ApiFactory.apiService.fetchData().awaitResponse()
            val responseData =
                result.body()?.let { weatherItemMapper.transfer(it) }
            Log.d("MyTag", responseData.toString())
            withContext(Dispatchers.Main) {
                binding.tvTempData.text = responseData?.temp.toString()
                binding.tvFeelsLikeData.text = responseData?.feels_like.toString()
                binding.tvPressureData.text = responseData?.pressure.toString()
            }
        }
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}