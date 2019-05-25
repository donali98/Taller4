package com.donali.taller4

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.donali.taller4.entities.BookWithAuthors
import com.donali.taller4.viewmodels.BookViewModel

class BookAdapter(val clickListener:(BookWithAuthors, TextView)->Unit, val bookViewModel: BookViewModel): RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    var books:List<BookWithAuthors> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_template,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        holder.bind(books[position])
    }


    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var tvBookTitle:TextView
        lateinit var cvFavorite: CheckBox

        fun bind(bookWithAuthor:BookWithAuthors) = with(itemView){
            tvBookTitle = findViewById(R.id.tv_book_title)
            cvFavorite = findViewById(R.id.cv_favorite)
            tvBookTitle.text = bookWithAuthor.book.title
            cvFavorite.isChecked = bookWithAuthor.book.isFavorite
           /* cvFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
                bookViewModel.updateFavorite(isChecked,bookWithAuthor.book.id)
            }*/
            cvFavorite.setOnClickListener {
                bookViewModel.updateFavorite(cvFavorite.isChecked,bookWithAuthor.book.id)

            }
            this.setOnClickListener{clickListener(bookWithAuthor,tvBookTitle)}
///*            for(authId in bookWithAuthor.authorsIdList){
//                Log.d("CUSTOM","authorID: $authId")
//            }*/

        }
    }
    /*
    Funcion que se ejecuta al momento en que ocurre un cambio en la tabla book
    (se agrega, modifica o elimina un registro), la cual actualiza el recycler
    con la nueva lista de libros
* */
    public fun setData(booksWithAuthors:List<BookWithAuthors> ){
        this.books = booksWithAuthors
        notifyDataSetChanged()
    }
}