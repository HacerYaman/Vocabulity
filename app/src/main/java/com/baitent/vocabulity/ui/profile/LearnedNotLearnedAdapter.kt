package com.baitent.vocabulity.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baitent.vocabulity.R
import com.baitent.vocabulity.data.model.CardItem

class LearnedNotLearnedAdapter(
    private var items: List<CardItem> // Adapter, hem learned hem de not learned verileri alabilir
) : RecyclerView.Adapter<LearnedNotLearnedAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val englishWordTextView: TextView = itemView.findViewById(R.id.englishWord)
        val turkishWordTextView: TextView = itemView.findViewById(R.id.turkishWord)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardItem = items[position]
        holder.englishWordTextView.text = cardItem.engWord
        holder.turkishWordTextView.text = cardItem.trWord
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newItems: List<CardItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
