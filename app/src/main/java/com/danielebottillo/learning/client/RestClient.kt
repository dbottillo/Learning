package com.danielebottillo.learning.client

import com.google.gson.Gson
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {

    val service: ChuckNorrisService by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.icndb.com/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        retrofit.create(ChuckNorrisService::class.java)
    }

    fun random(): Observable<ApiJokeResponse> {
        return service.random()
    }
}