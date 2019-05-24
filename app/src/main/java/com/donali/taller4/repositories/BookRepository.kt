package com.donali.taller4.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.donali.taller4.daos.BookDao
import com.donali.taller4.entities.Book
import com.donali.taller4.entities.BookWithAuthors

class BookRepository(private val bookDao: BookDao) {

    fun getAll():LiveData<List<BookWithAuthors>> = bookDao.getAllBooks()

    @WorkerThread
    suspend fun insert(book: Book):Long = bookDao.insertBook(book)

    @WorkerThread
    suspend fun updateFavorite(isFv: Boolean,bookID:Long) = bookDao.updateFavorite(isFv,bookID)


}