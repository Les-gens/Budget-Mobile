package com.example.budget.ui.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EntryViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Super robot"
    }
    private val _text2 = MutableLiveData<String>().apply {
        value = "Super robot"
    }
    private val _text3 = MutableLiveData<String>().apply {
        value = "Super robot"
    }
    private val _text4 = MutableLiveData<String>().apply {
        value = "Super robot"
    }
    val text1: LiveData<String> = _text
    val text2: LiveData<String> = _text2
    val text3: LiveData<String> = _text3
    val text4: LiveData<String> = _text4
}