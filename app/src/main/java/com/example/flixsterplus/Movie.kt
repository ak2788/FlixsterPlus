package com.example.flixsterplus

import org.json.JSONObject

class Movie (jsonObject: JSONObject){

    val title: String = jsonObject.getString("title")
    val overview: String = jsonObject.getString("overview")
    private val posterPath: String = jsonObject.getString("poster_path")

    fun getPosterUrl(): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}