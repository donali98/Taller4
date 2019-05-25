package com.donali.taller4.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donali.taller4.entities.Editorial
@Dao
interface EditorialDao {
    @Query("select * from editorial limit 1")
    fun getFirst(): Editorial

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(editorial: Editorial):Long

    @Query("delete from editorial")
    fun deleteAll()
}