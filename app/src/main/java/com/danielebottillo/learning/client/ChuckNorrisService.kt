package com.danielebottillo.learning.client

import io.reactivex.Observable
import retrofit2.http.GET

interface ChuckNorrisService {

    @GET("/jokes/random")
    fun random(): Observable<ApiJokeResponse>

}