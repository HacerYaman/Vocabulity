package com.baitent.vocabulity.ui.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.baitent.vocabulity.R
import com.baitent.vocabulity.data.model.CardItem

class CardAdapter(
    private var items: List<CardItem>,
    private val onLongClick: (CardItem) -> Unit,
    private val onDetailClick: (CardItem) -> Unit // Detail button için lambda fonksiyonu ekliyoruz
) : BaseAdapter() {

    fun updateItems(newItems: List<CardItem>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_card, parent, false)
        val englishWordtv: TextView = view.findViewById(R.id.english)
        val turkishWordtv: TextView = view.findViewById(R.id.turkish)
        val detailButton: ImageButton = view.findViewById(R.id.detailButton)

        val cardItem = items[position]

        englishWordtv.text = cardItem.engWord
        turkishWordtv.text = cardItem.trWord

        // Detail buttona tıklanınca detaile geçiş
        detailButton.setOnClickListener {
            onDetailClick(cardItem)
        }

        // Uzun basıldığında işlem yap
        view.setOnLongClickListener {
            onLongClick(cardItem)
            true
        }

        return view
    }
}
