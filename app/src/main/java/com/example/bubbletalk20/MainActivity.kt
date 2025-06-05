package com.example.bubbletalk20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ----- 這一行是新增的：App 一打開就啟動 MusicService -----
        Intent(this, MusicService::class.java).also { musicIntent ->
            startService(musicIntent)
        }

        // 以下原本就有的 A–Z 按鈕綁定程式碼：
        val btnStart = findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener {
            val intent = Intent(this, AlphabetActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        // 當 MainActivity 被銷毀（App 被關閉）時，停止背景音樂 Service
        Intent(this, MusicService::class.java).also { musicIntent ->
            stopService(musicIntent)
        }
    }
}