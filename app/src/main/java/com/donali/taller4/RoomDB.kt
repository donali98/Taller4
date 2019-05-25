package com.donali.taller4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.donali.taller4.daos.AuthorBooksDao
import com.donali.taller4.daos.AuthorDao
import com.donali.taller4.daos.BookDao
import com.donali.taller4.daos.EditorialDao
import com.donali.taller4.entities.Author
import com.donali.taller4.entities.AuthorBooks
import com.donali.taller4.entities.Book
import com.donali.taller4.entities.Editorial
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Book::class, Author::class, AuthorBooks::class,Editorial::class], version = 3,exportSchema = false)

public abstract class RoomDB: RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun authorDao(): AuthorDao
    abstract fun authorBooksDao(): AuthorBooksDao
    abstract fun editorialDao(): EditorialDao

    private class RoomDBCallback(private val scope: CoroutineScope):RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {database->
                scope.launch(Dispatchers.IO){
                    populateDatabase(database.authorDao(),database.bookDao(),database.authorBooksDao(),database.editorialDao())
                }
            }
        }
        suspend fun populateDatabase(
            authorDao: AuthorDao,
            bookDao: BookDao,
            authorBooksDao: AuthorBooksDao,
            editorialDao:EditorialDao
        ){
/*
            authorBooksDao.deleteAll()
            bookDao.deleteBooks()
            editorialDao.deleteAll()
            authorDao.deleteAll()
*/


//            Log.d("CUSTOM",author.toString())
            authorDao.insertAuthor(Author("Stephen King"))
            editorialDao.insert(Editorial("Planet"))
        }
    }


    companion object{
        @Volatile
        private var INSTANCE:RoomDB? = null

        fun getInstance(
            appContext: Context,
            scope:CoroutineScope
        )
                :RoomDB{
            val tmp = INSTANCE
            if(tmp!=null) return tmp
            synchronized(this){
                val instance = Room.databaseBuilder(appContext,RoomDB::class.java,"Library")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}