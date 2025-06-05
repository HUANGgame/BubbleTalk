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
            // 2. 設定為無限循環播放
            isLooping = true
            // 3. 設定適當的音量，例如 50%
            setVolume(0.3f, 0.3f)
            // 4. 開始播放
            start()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 當 Service 被系統意外殺掉後要重啟所使用的旗標
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // 5. 停止並釋放 MediaPlayer
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onBind(intent: Intent?): IBinder? {
        // 這是一個純粹用 startService()/stopService() 的 Service，不需要 bind
        return null
    }
}
