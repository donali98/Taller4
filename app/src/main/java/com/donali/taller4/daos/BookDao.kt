package com.donali.taller4.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donali.taller4.entities.Book
import com.donali.taller4.entities.BookWithAuthors

@Dao
interface BookDao {

    @Query("select * from book")
    fun getAllBooks(): LiveData<List<BookWithAuthors>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book):Long

    @Query("delete from book")
    fun deleteBooks()

    @Query("update book set isFavorite = :isFv where book.id = :bookID")
    suspend fun updateFavorite(isFv:Boolean, bookID:Long)
}