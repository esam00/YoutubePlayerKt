package com.essam.youtubeplayerkt.utils

import android.content.Context
import android.net.ConnectivityManager

fun isNetworkConnected(mContext: Context): Boolean {
    val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val ni = cm.activeNetworkInfo
    return ni != null
}

fun representStatistic(num: Int): String =
    when {
        num / 1000 >= 1 && num / 1000000 < 1 -> "${num / 1000}K"
        num / 1000000 >= 1 -> "${num / 1000000}M"
        else -> num.toString()
    }