package com.example.bubbletalk20

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LetterAdapter(
    private val letters: List<Char>,
    private val onClick: (Char) -> Unit
) : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    inner class LetterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textLetter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_letter, parent, false)
        return LetterViewHolder(view)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val letter = letters[position]
        holder.textView.text = letter.toString()
        holder.itemView.setOnClickListener { onClick(letter) }
    }

    override fun getItemCount(): Int = letters.size
}
