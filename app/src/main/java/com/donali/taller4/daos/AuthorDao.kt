package com.donali.taller4.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donali.taller4.entities.Author

@Dao
interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(author: Author):Long

    @Query("select * from author limit 1")
    fun getFirstAuthor():Author

    @Query("select * from  author where id = :auId")
    fun getAuthorById(auId:Long):LiveData<Author>

    @Query("select * from author")
    fun getAllAuthors(): LiveData<List<Author>>

    @Query("delete from author")
    fun deleteAll()
}