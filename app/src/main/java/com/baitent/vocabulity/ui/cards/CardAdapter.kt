package com.baitent.vocabulity.ui.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baitent.vocabulity.R
import com.baitent.vocabulity.data.model.CardItem

class CardAdapter(
    private val items: List<CardItem>
) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_card, parent, false)
        val wordTextView: TextView = view.findViewById(R.id.wordTextView)
        val item = getItem(position) as CardItem
        wordTextView.text = item.word
        return view
    }
}