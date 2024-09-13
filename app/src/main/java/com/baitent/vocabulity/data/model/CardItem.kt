package com.baitent.vocabulity.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardItem(
    @PrimaryKey val engWord: String,  // İngilizce kelimeyi primary key olarak kullanıyoruz
    val trWord: String,
    val description: String = "",     // Opsiyonel bir açıklama, boş bırakılabilir
    val status: String = "not_learned" // Varsayılan değer olarak 'not_learned'
)