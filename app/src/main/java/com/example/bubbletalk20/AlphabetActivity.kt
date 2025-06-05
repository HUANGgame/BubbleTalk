package com.example.bubbletalk20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AlphabetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alphabet)

        // A-Z 按鈕A-Z 單字列表頁
        val btnAlphabet = findViewById<Button>(R.id.btn_alphabet)
        btnAlphabet.setOnClickListener {
            val intent = Intent(this, AZActivity::class.java)
            startActivity(intent)
        }

        // Theme 按鈕主題頁
        val btnTheme = findViewById<Button>(R.id.btn_theme)
        btnTheme.setOnClickListener {
            val intent = Intent(this, ThemeActivity::class.java)
            startActivity(intent)
        }
    }
}

