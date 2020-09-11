package com.essam.youtubeplayerkt.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.essam.youtubeplayerkt.R
import com.essam.youtubeplayerkt.adapter.VideoListAdapter
import com.essam.youtubeplayerkt.model.Item
import com.essam.youtubeplayerkt.model.VideoListResponse
import com.essam.youtubeplayerkt.ui.player.VideoPlayerActivity
import com.essam.youtubeplayerkt.utils.VIDEO_ITEM
import com.essam.youtubeplayerkt.utils.isNetworkConnected
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(),
    HomeListeners, VideoListAdapter.OnItemClickListener {

    private var mVideoItemsList = ArrayList<Item>()
    private val mVideoListAdapter = VideoListAdapter(this,this)
    private val presenter = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initSwipeRefresh()
        initRecyclerView()
        getTrendingVideos()

    }

    private fun initSwipeRefresh(){
        swipeRefresh.setOnRefreshListener {
            getTrendingVideos()
        }
        swipeRefresh.isRefreshing = true
    }

    private fun initRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(this)
        video_rv.adapter = mVideoListAdapter
        video_rv.layoutManager = linearLayoutManager
    }

    private fun getTrendingVideos(){
        if (isNetworkConnected(this)){
            swipeRefresh.isRefreshing = true
            presenter.getTrendingMovies()
        }else{
            swipeRefresh.isRefreshing = false
            logo_iv.visibility = View.VISIBLE
            val snackbar = Snackbar.make(swipeRefresh,R.string.no_internet,Snackbar.LENGTH_LONG)
            snackbar.setBackgroundTint(resources.getColor(R.color.colorAccent))
            snackbar.show()

        }
    }

    private fun updateUi(response: VideoListResponse?){
        swipeRefresh.isRefreshing = false
        if (response != null){
            mVideoItemsList = response.items as ArrayList<Item>
            mVideoListAdapter.setItems(mVideoItemsList)
            mVideoListAdapter.notifyDataSetChanged()
            logo_iv.visibility = View.INVISIBLE
            Toast.makeText(this, R.string.videos_updated, Toast.LENGTH_SHORT).show()
        }else{
            logo_iv.visibility = View.VISIBLE
            Toast.makeText(this, R.string.network_request_failed, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTrendingMoviesLoaded(videoListResponse: VideoListResponse?) {
        updateUi(videoListResponse)
    }

    override fun onItemClicked(position: Int) {
        val item = mVideoItemsList[position]
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra(VIDEO_ITEM,item)
        startActivity(intent)
    }
}
