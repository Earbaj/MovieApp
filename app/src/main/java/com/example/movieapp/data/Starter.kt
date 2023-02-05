package com.example.movieapp.data

import com.squareup.moshi.Json

data class Starter(
    @Json(name = "page")
    val page: Long,
    @Json(name = "results")
    val results: List<Result>,
)
