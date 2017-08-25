package com.danielebottillo.learning.client

import com.google.gson.annotations.SerializedName

class ApiJokeResponse(val type: String, val value: ApiJoke)

class ApiJoke(val id: Int, @SerializedName("joke") val text: String)