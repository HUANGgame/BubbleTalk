package com.example.bubbletalk20

import android.graphics.Color
import android.graphics.Typeface
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.View.TEXT_ALIGNMENT_VIEW_START
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class LetterWordsAdapter(
    private val words: List<String>,
    private val tts: TextToSpeech
) : RecyclerView.Adapter<LetterWordsAdapter.WordViewHolder>() {

    inner class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textViewWord)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        val raw = words[position]


        holder.textView.text = raw

        // 設定字體大小、粗細、顏色、靠左對齊
        holder.textView.textSize = 22f
        holder.textView.setTypeface(null, Typeface.BOLD)
        holder.textView.setTextColor(Color.BLACK)
        holder.textView.textAlignment = TEXT_ALIGNMENT_VIEW_START


        val englishOnly = raw.substringBefore("(")


        holder.itemView.setOnClickListener {
            tts.speak(
                englishOnly,
                TextToSpeech.QUEUE_FLUSH,
                null,
                englishOnly
            )
        }
    }

    override fun getItemCount(): Int = words.size
}
