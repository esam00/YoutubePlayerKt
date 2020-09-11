package com.essam.youtubeplayerkt.ui.home

import com.essam.youtubeplayerkt.model.VideoListResponse

interface HomeListeners {

    fun onTrendingMoviesLoaded(videoListResponse: VideoListResponse?)
}