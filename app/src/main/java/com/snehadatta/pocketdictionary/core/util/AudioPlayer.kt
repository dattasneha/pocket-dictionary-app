package com.snehadatta.pocketdictionary.core.util

import android.content.Context
import android.media.MediaPlayer

class AudioPlayer(private  val context: Context) {
    private var mediaplayer: MediaPlayer ? = null

    fun play(url: String) {
        stop()
        try {
            mediaplayer = MediaPlayer().apply {
                setDataSource(url)
                setOnPreparedListener { start() }
                setOnCompletionListener { stop() }
                prepareAsync()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun stop() {
       mediaplayer?.release()
       mediaplayer = null
    }


}