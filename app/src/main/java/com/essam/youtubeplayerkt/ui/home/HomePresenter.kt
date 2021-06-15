package com.essam.youtubeplayerkt.ui.home

import android.util.Log
import com.essam.youtubeplayerkt.api.ApiClient
import com.essam.youtubeplayerkt.model.VideoListResponse
import com.essam.youtubeplayerkt.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter: HomePresenterInterface {
    private val TAG = "HomePresenter"

    var view: HomeView? = null
    /**
     * This method simply creates a Retrofit instance and make a network call to Youtube Data Api
     * and returns a list of videos that match the API request parameters.
     * For more details checkout the documentation : https://developers.google.com/youtube/v3/docs/videos/list
     */

    override fun attachView(view: HomeView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

     override fun getTrendingMovies() {
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
                    view?.onTrendingMoviesLoaded(response.body()?.items ?: arrayListOf())
                else
                    view?.onError(response.errorBody().toString())
            }

            override fun onFailure(call: Call<VideoListResponse>, t: Throwable) {
                Log.e(TAG, "error loading videos : $t")

                view?.onError(t.localizedMessage)
            }
        })
    }
}