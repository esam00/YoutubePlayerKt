package com.essam.youtubeplayerkt.ui.home

import android.util.Log
import com.essam.youtubeplayerkt.api.ApiClient
import com.essam.youtubeplayerkt.model.VideoListResponse
import com.essam.youtubeplayerkt.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(val homeListeners: HomeListeners) {
    private val TAG = "HomePresenter"

    /**
     * This method simply creates a Retrofit instance and make a network call to Youtube Data Api
     * and returns a list of videos that match the API request parameters.
     * For more details checkout the documentation : https://developers.google.com/youtube/v3/docs/videos/list
     */

     fun getTrendingMovies() {
        Log.i(TAG, "Api request >>>>>>> getVideos")

        val call = ApiClient.getTrendingVideos(
            PART_QUERY_VALUE,
            CHART_QUERY_VALUE,
            REGION_CODE_QUERY_VALUE,
            MAX_RESULT_QUERY_VALUE,
            GOOGLE_API_KEY
        )

        call.enqueue(object : Callback<VideoListResponse> {
            override fun onResponse(call: Call<VideoListResponse>, response: Response<VideoListResponse>) {
                Log.i(TAG, "Get videos Response : $response ")
                if (response.isSuccessful)
                    homeListeners.onTrendingMoviesLoaded(response.body())
            }

            override fun onFailure(call: Call<VideoListResponse>, t: Throwable) {
                Log.e(TAG, "error loading videos : $t")

                homeListeners.onTrendingMoviesLoaded(null)
            }
        })
    }
}