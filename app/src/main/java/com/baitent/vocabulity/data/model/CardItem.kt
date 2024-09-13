package com.baitent.vocabulity.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardItem(
    @PrimaryKey val engWord: String,
    val trWord: String,
    val exampleSentence: String,
    var status: String = "all"  // Başlangıçta 'notLearned' olarak ayarladık
)
