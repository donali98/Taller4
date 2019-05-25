package com.donali.taller4.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "editorial")
data class Editorial(
    @ColumnInfo(name = "name") val name: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}