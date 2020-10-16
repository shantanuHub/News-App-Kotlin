package com.example.kotlin_recyclerview_retrofit.retrofit


import com.example.kotlin_recyclerview_retrofit.models.News
import com.example.kotlin_recyclerview_retrofit.others.API_KEY
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getNews(@Query("country") country: String  , @Query("page") page: Int) : Call<News>

}
