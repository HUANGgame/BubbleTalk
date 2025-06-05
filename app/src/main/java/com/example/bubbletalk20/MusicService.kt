package com.example.bubbletalk20

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder


class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()

        mediaPlayer = MediaPlayer.create(this, R.raw.goodnight).apply {
          
            isLooping = true
            
            setVolume(0.3f, 0.3f)
          
            start()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
       
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onBind(intent: Intent?): IBinder? {
       
        return null
    }
}
