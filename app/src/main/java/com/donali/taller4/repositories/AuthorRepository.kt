package com.donali.taller4.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.donali.taller4.daos.AuthorDao
import com.donali.taller4.entities.Author

class AuthorRepository(val authorDao: AuthorDao) {

    fun getAuthorById(auid:Long):LiveData<Author> = authorDao.getAuthorById(auid)

    fun getFirstAuthor():Author = authorDao.getFirstAuthor()

    fun getAll():LiveData<List<Author>> = authorDao.getAllAuthors()
    fun getAllNoLiveData():List<Author> = authorDao.getAllNoLiveData()

    @WorkerThread
    suspend fun insert(author: Author):Long = authorDao.insertAuthor(author)
}