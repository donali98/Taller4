package com.donali.taller4.entities

import androidx.room.Embedded
import androidx.room.Relation


data class BookWithAuthors (
    @Embedded val book:Book,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId",
        entity = AuthorBooks::class,
        projection = ["authorId"]
    )val authorsIdList:List<Long>
){
}