
package com.donali.taller4.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.donali.taller4.daos.AuthorBooksDao
import com.donali.taller4.entities.Author
import com.donali.taller4.entities.AuthorBooks

class AuthorBooksRepository (val authorBooksDao: AuthorBooksDao){
    @WorkerThread
    suspend fun insert(authorBook:AuthorBooks):Long = authorBooksDao.insert(authorBook)
    fun getAuthorsFromBook(bookId:Long):LiveData<List<Author>> = authorBooksDao.getAuthorsFromBook(bookId)
    fun getAll():LiveData<List<AuthorBooks>> = authorBooksDao.getAll()
}