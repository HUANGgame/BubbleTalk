package com.example.bubbletalk20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       
        Intent(this, MusicService::class.java).also { musicIntent ->
            startService(musicIntent)
        }

       
        val btnStart = findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener {
            val intent = Intent(this, AlphabetActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        
        Intent(this, MusicService::class.java).also { musicIntent ->
            stopService(musicIntent)
        }
    }
}
