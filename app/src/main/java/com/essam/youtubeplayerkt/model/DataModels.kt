package com.essam.youtubeplayerkt.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StandardThumbnail(val url: String?): Serializable

data class Thumbnail(val standard: StandardThumbnail?): Serializable

data class Video(val title: String, val description: String, val publishedAt: String, val thumbnails: Thumbnail): Serializable

data class Item(val id:String,
                @SerializedName ("snippet") val video: Video, val statistics:Statistics): Serializable

data class VideoListResponse(val items : List<Item>): Serializable

data class Statistics(val viewCount: String = "0", val likeCount: String = "0", val dislikeCount: String = "0" ) : Serializable