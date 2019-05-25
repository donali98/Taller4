package com.donali.taller4


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.donali.taller4.entities.Book
import com.donali.taller4.entities.BookWithAuthors
import com.donali.taller4.helpers.ActivityHelper
import com.donali.taller4.viewmodels.BookViewModel


class BookFragment : Fragment() {
    lateinit var bookList: RecyclerView
    lateinit var activityHelper: ActivityHelper
    lateinit var bookViewModel: BookViewModel
    lateinit var btnAdd: Button
    lateinit var etTitle: EditText
    lateinit var bookAdapter: BookAdapter

    var names: ArrayList<String> = arrayListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityHelper = context as ActivityHelper
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book, container, false)
        bookList = view.findViewById(R.id.rv_books)
        btnAdd = view.findViewById(R.id.btn_add)
        etTitle = view.findViewById(R.id.et_title)

        bookViewModel = activityHelper.getViewModel()
        val clickListen = fun(bookWithAuthor: BookWithAuthors, tvBookName: TextView) {

            names = arrayListOf()

            for (item in bookWithAuthor.authorsIdList) {
                names.add(item.toString())
            }
            val bookInfoFragment = BookInfoFragment.newInstance(tvBookName.text.toString(), names,bookWithAuthor.book.editorialId.toString())
            activityHelper.getCustomSupportFragmentMananager().beginTransaction()
                .replace(R.id.fl_main_container, bookInfoFragment)
                .addToBackStack("c")
                .commit()
        }
/*        cvFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            bookViewModel.updateFavorite(isChecked,)
        }*/
        bookAdapter = BookAdapter(clickListen,bookViewModel)


        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookList.apply {
            setHasFixedSize(true)
            adapter = bookAdapter
            layoutManager = activityHelper.getLayoutManager()
        }
        bookViewModel.getAllBooks().observe(this, Observer {
            bookAdapter.setData(it)

        })

        btnAdd.setOnClickListener {
            bookViewModel.insertBook(Book(etTitle.text.toString()))
        }
    }


}
