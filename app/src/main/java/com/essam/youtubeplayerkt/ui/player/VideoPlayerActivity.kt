package com.essam.youtubeplayerkt.ui.player

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import com.essam.youtubeplayerkt.R
import com.essam.youtubeplayerkt.model.Item
import com.essam.youtubeplayerkt.utils.GOOGLE_API_KEY
import com.essam.youtubeplayerkt.utils.VIDEO_ITEM
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_video_player.*
import kotlinx.android.synthetic.main.video_statistics.*

class VideoPlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private  val TAG = VideoPlayerActivity::class.java.simpleName
    private var mVideoId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        // receive video item from intent and populate its data to ui
        val mItem = intent.getSerializableExtra(VIDEO_ITEM) as Item
        populateVideoData(mItem)

        // start initializing and loading youtube video
        mVideoId = mItem.id
        youtubePlayer.initialize(GOOGLE_API_KEY,this)

    }

    private fun populateVideoData(mItem: Item) {
        tv_title.text = (mItem.video.title)
        tv_description.text = (mItem.video.description)
        tv_views_count.text = (mItem.statistics.viewCount)
        tv_likes_count.text = (mItem.statistics.likeCount)
        tv_dislikes_count.text = (mItem.statistics.dislikeCount)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, p2: Boolean) {
        Log.i(TAG, "onInitializationSuccess: START LOADING VIDEO ...")

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) youTubePlayer?.setFullscreen(true)

        youTubePlayer?.loadVideo(mVideoId)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        Log.i(TAG, "onInitializationFailure: FAILED TO INITIALIZE YOUTUBE PLAYER ...")
    }
}