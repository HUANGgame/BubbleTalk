package com.example.bubbletalk20


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ThemeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ThemeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)

        // 返回
        val btnBack: Button = findViewById(R.id.btn_back_theme)
        btnBack.setOnClickListener { finish() }

        val themes = listOf(
            "日常生活daily", "服裝clothes", "天氣weather", "家庭family", "學校school",
            "日期date", "食物food", "顏色color", "動物animal", "運動exercise"
        )

        recyclerView = findViewById(R.id.recyclerViewThemes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ThemeAdapter(themes) { theme ->
            val intent = Intent(this, ThemeWordsActivity::class.java)
            intent.putExtra("theme", theme)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}
