package com.baitent.vocabulity.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baitent.vocabulity.R
import com.baitent.vocabulity.data.model.CardItem

class SearchAdapter(
    private var items: List<CardItem>,
    private val onItemClick: (CardItem) -> Unit // Tıklama işlemi için lambda fonksiyonu
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val englishWordTextView: TextView = itemView.findViewById(R.id.englishWord)
        val turkishWordTextView: TextView = itemView.findViewById(R.id.turkishWord)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val wordItem = items[position]
        holder.englishWordTextView.text = wordItem.engWord
        holder.turkishWordTextView.text = wordItem.trWord

        // Item'e tıklanınca bu işlemi çağır
        holder.itemView.setOnClickListener {
            onItemClick(wordItem)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newItems: List<CardItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}

