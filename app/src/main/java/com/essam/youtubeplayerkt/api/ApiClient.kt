package com.essam.youtubeplayerkt.api

import com.essam.youtubeplayerkt.model.VideoListResponse
import com.essam.youtubeplayerkt.utils.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val mApiService: ApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApiService = retrofit.create(ApiService::class.java)
    }

    fun getTrendingVideos(
        part: String, chart: String, regionCode: String,
        maxResults: Int, apiKy: String
    ): Call<VideoListResponse> {
        return mApiService.getVideos(part, chart, regionCode, maxResults, apiKy)
    }
}
