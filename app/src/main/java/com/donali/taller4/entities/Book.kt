package com.donali.taller4.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "book",
    foreignKeys = [ForeignKey(
        entity = Editorial::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("editorial_id")
    )]
)
data class Book(
    @ColumnInfo(name = "title") val title: String
) {
    @ColumnInfo(name = "isFavorite")
    var isFavorite = false
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    @ColumnInfo(name = "editorial_id")
    var editorialId: Long = 0

}