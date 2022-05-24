package ru.gb.materialapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {
    val search = MutableLiveData<String>()

    fun setSearch(search: String) {
        this.search.value = search
    }
}