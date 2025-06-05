package com.example.bubbletalk20

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AZActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_az)

        // 返回按鈕
        val btnBack = findViewById<Button>(R.id.btn_back_to_main)
        btnBack.setOnClickListener {
            finish()
        }

        // A~Z 三列
        val gridLayout = findViewById<GridLayout>(R.id.gridLayoutAZ)
        val letters = ('A'..'Z').toList()

        for (letter in letters) {
            val textView = TextView(this)
            textView.text = letter.toString()
            textView.textSize = 24f
            textView.setTextColor(resources.getColor(android.R.color.white, null))
            textView.gravity = Gravity.CENTER
            textView.setPadding(16, 16, 16, 16)

            val params = GridLayout.LayoutParams()
            params.width = 0
            params.height = GridLayout.LayoutParams.WRAP_CONTENT
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            textView.layoutParams = params

            textView.setOnClickListener {
                val intent = Intent(this, LetterWordsActivity::class.java)
                intent.putExtra("letter", letter.toString())
                startActivity(intent)
            }
            gridLayout.addView(textView)
        }
    }
}
