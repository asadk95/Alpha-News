package com.app.asad.alphanews.api

import com.app.asad.alphanews.models.NewsResponse
import com.app.asad.alphanews.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        countryCode:String="pk",
        @Query("page")
        pageNumber:Int =1,
        @Query("apiKey")
        apiKey:String =API_KEY
    ):Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery:String,
        @Query("page")
        pageNumber:Int=1,
        @Query("apiKey")
        apiKey: String= API_KEY
    ):Response<NewsResponse>
}