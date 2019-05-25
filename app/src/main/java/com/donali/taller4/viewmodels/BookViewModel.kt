package com.donali.taller4.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.donali.taller4.RoomDB
import com.donali.taller4.entities.*
import com.donali.taller4.repositories.AuthorBooksRepository
import com.donali.taller4.repositories.AuthorRepository
import com.donali.taller4.repositories.BookRepository
import com.donali.taller4.repositories.EditorialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(app: Application) : AndroidViewModel(app) {
    private val bookRepository: BookRepository
    private val authorRepository: AuthorRepository
    private val authorBooksRepository: AuthorBooksRepository
    private val editorialRepository: EditorialRepository


    init {
        val bookDao = RoomDB.getInstance(app, viewModelScope).bookDao()
        val authorDao = RoomDB.getInstance(app, viewModelScope).authorDao()
        val authorBookDao = RoomDB.getInstance(app, viewModelScope).authorBooksDao()
        val editorialDao = RoomDB.getInstance(app, viewModelScope).editorialDao()
        bookRepository = BookRepository(bookDao)
        authorRepository = AuthorRepository(authorDao)
        authorBooksRepository = AuthorBooksRepository(authorBookDao)
        editorialRepository = EditorialRepository(editorialDao)

    }

    fun getFirstEditorial(): Editorial = editorialRepository.getFirst()
    fun getEditorialById(eId:Long):LiveData<Editorial> = editorialRepository.getById(eId)
    fun getAllBooks(): LiveData<List<BookWithAuthors>> = bookRepository.getAll()
    fun getFirstAuthor(): Author = authorRepository.getFirstAuthor()
    fun getAuthorById(auId: Long): LiveData<Author> = authorRepository.getAuthorById(auId)
    fun getAllAuthors(): LiveData<List<Author>> = authorRepository.getAll()

    fun insertBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        val editorial = editorialRepository.getFirst()
        book.editorialId = editorial.id
        val authors = authorRepository.getAllNoLiveData()
        val bookId = bookRepository.insert(book)

        for (item in authors) {
            insertAuthorBook(AuthorBooks(item.id, bookId))
        }
/*        val bookId = bookRepository.insert(book)
val author = getFirstAuthor()
val authorId = author.id
insertAuthorBook(AuthorBooks(authorId,bookId))*/
    }

    fun insertAuthorBook(authorBook: AuthorBooks) = viewModelScope.launch(Dispatchers.IO) {
        authorBooksRepository.insert(authorBook)
    }

    fun insertEditorial(editorial: Editorial) = viewModelScope.launch(Dispatchers.IO) {
        editorialRepository.insert(editorial)
    }

    fun updateFavorite(isFv: Boolean, bookId: Long) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.updateFavorite(isFv, bookId)
    }

}