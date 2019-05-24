package com.donali.taller4.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "author_books",
    primaryKeys = ["authorId","bookId"],
    foreignKeys = [
        ForeignKey(entity = Book::class,parentColumns = ["id"],childColumns = ["bookId"]),
        ForeignKey(entity = Author::class,parentColumns = ["id"],childColumns = ["authorId"])
    ]
)
data class AuthorBooks(
    val authorId:Long,
    val bookId:Long
)