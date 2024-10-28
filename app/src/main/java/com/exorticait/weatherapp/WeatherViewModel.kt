package com.exorticait.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exorticait.weatherapp.api.RetrofitInstance
import com.exorticait.weatherapp.data.NetworkResponse
import com.exorticait.weatherapp.data.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    val TAG = "WeatherViewModel"

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherLiveData = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherLiveData: LiveData<NetworkResponse<WeatherModel>> = _weatherLiveData


    fun getData(city: String) {
        Log.i(TAG, "CityName: $city")
        viewModelScope.launch {
            _weatherLiveData.value = NetworkResponse.Loading
            try {
                val response = weatherApi.getWeather(city = city, apiKey = Constant.API_KEY)
                if (response.isSuccessful) {
                    Log.i(TAG, "Data: ${response.body().toString()}")
                    response.body()?.let {
                        _weatherLiveData.value = NetworkResponse.Success(it)
                    }
                } else {
                    Log.i(TAG, "Error: ${response.message()}")
                    _weatherLiveData.value = NetworkResponse.Failure("Unable to connect")
                }
            } catch (e: Exception){
                _weatherLiveData.value = NetworkResponse.Failure("Unable to get data")
            }

        }

    }
}