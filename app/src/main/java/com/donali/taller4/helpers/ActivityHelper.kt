package com.donali.taller4.helpers

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.donali.taller4.viewmodels.BookViewModel

interface ActivityHelper {
    fun getLayoutManager(): RecyclerView.LayoutManager
    fun getCustomSupportFragmentMananager(): FragmentManager
    fun getViewModel(): BookViewModel
}