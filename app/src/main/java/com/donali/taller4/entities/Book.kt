package com.donali.taller4.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @ColumnInfo(name = "title") val title: String
) {
    @ColumnInfo(name = "isFavorite")
    var isFavorite = false
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}