package com.exorticait.weatherapp.api

import com.exorticait.weatherapp.data.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("key") apiKey: String
    ): Response<WeatherModel>
}