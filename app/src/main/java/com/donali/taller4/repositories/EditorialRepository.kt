package com.donali.taller4.repositories

import androidx.lifecycle.LiveData
import com.donali.taller4.daos.EditorialDao
import com.donali.taller4.entities.Editorial

class EditorialRepository(private val editorialDao: EditorialDao) {
    fun getFirst():Editorial = editorialDao.getFirst()
    fun getById(eId:Long): LiveData<Editorial> = editorialDao.getById(eId)
    suspend fun insert(editorial: Editorial) = editorialDao.insert(editorial)
    fun deleteAll() = editorialDao.deleteAll()
}