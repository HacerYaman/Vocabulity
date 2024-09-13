package com.baitent.vocabulity.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baitent.vocabulity.R
import com.baitent.vocabulity.data.model.CardItem

class ProfileAdapter(
    private var items: List<CardItem>
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    fun updateItems(newItems: List<CardItem>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val englishWordTextView: TextView = itemView.findViewById(R.id.englishWord)
        private val turkishWordTextView: TextView = itemView.findViewById(R.id.turkishWord, )

        fun bind(cardItem: CardItem) {
            englishWordTextView.text = cardItem.engWord
            turkishWordTextView.text = cardItem.trWord
        }
    }
}