package com.essam.youtubeplayerkt.ui.home

import com.essam.youtubeplayerkt.model.Item

interface HomePresenterInterface {
    fun attachView(view: HomeView)
    fun detachView()
    fun getTrendingMovies()
}

interface HomeView {
    fun onTrendingMoviesLoaded(items: List<Item>)
    fun onError(message: String)
}