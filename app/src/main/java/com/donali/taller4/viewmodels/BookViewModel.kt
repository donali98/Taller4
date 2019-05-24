package com.donali.taller4.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.donali.taller4.RoomDB
import com.donali.taller4.entities.Author
import com.donali.taller4.entities.AuthorBooks
import com.donali.taller4.entities.Book
import com.donali.taller4.entities.BookWithAuthors
import com.donali.taller4.repositories.AuthorBooksRepository
import com.donali.taller4.repositories.AuthorRepository
import com.donali.taller4.repositories.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(app: Application): AndroidViewModel(app) {
    private val bookRepository:BookRepository
    private val authorRepository:AuthorRepository
    private val authorBooksRepository:AuthorBooksRepository


    init {
        val bookDao = RoomDB.getInstance(app,viewModelScope).bookDao()
        val authorDao = RoomDB.getInstance(app,viewModelScope).authorDao()
        val authorBookDao = RoomDB.getInstance(app,viewModelScope).authorBooksDao()
        bookRepository = BookRepository(bookDao)
        authorRepository = AuthorRepository(authorDao)
        authorBooksRepository = AuthorBooksRepository(authorBookDao)
    }

    fun getAllBooks():LiveData<List<BookWithAuthors>> = bookRepository.getAll()
    fun getFirstAuthor():Author = authorRepository.getFirstAuthor()
    fun getAuthorById(auId:Long):LiveData<Author> = authorRepository.getAuthorById(auId)

    fun insertBook(book: Book) = viewModelScope.launch(Dispatchers.IO){
        val bookId = bookRepository.insert(book)
        val author = getFirstAuthor()
        val authorId = author.id
        insertAuthorBook(AuthorBooks(authorId,bookId))
    }

    fun insertAuthorBook(authorBook: AuthorBooks) = viewModelScope.launch (Dispatchers.IO){
        authorBooksRepository.insert(authorBook)
    }

    fun updateFavorite(isFv:Boolean,bookId:Long) = viewModelScope.launch (Dispatchers.IO){
        bookRepository.updateFavorite(isFv,bookId)
    }

}