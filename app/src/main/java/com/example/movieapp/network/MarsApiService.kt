package com.example.movieapp.network

import com.example.movieapp.data.Starter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//private const val BASE_URL =
//    "https://android-kotlin-fun-mars-server.appspot.com"
private const val BASE_URL =
    "https://api.themoviedb.org/3/movie/"

//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create())
//    .baseUrl(BASE_URL)
//    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
//https://api.themoviedb.org/3/movie/popular?api_key=bbf5a3000e95f1dddf266b5e187d4b21
interface MarsApiService{
//    @GET("photos")
//    suspend fun getPhotos() : String
    @GET("popular?api_key=bbf5a3000e95f1dddf266b5e187d4b21")
    suspend fun getPhotos() : Starter
}

object MarsApi{
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}

