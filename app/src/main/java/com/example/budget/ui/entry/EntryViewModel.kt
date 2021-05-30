package com.example.budget.ui.entry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budget.models.EntriesModel
import com.example.budget.repositories.EntriesRepository
import com.example.budget.utils.FirebaseUtils.db
import com.google.firebase.firestore.QuerySnapshot
import java.util.*

class EntryViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Super robot"
    }
    private val _text2 = MutableLiveData<String>().apply {
        value = "Cadeau"
    }
    private val _text3 = MutableLiveData<String>().apply {
        value = "15€"
    }
    private val _text4 = MutableLiveData<String>().apply {
        value = ""
    }
    val text1: LiveData<String> = _text
    val text2: LiveData<String> = _text2
    val text3: LiveData<String> = _text3
    val text4: LiveData<String> = _text4





}