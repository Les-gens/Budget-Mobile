package com.example.budget.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budget.models.EntriesModel
import com.example.budget.repositories.EntriesRepository
import com.google.firebase.firestore.QuerySnapshot
import java.util.*

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val repo = EntriesRepository()
    var entries: MutableLiveData<List<EntriesModel>> = MutableLiveData()
    val TAG = "ENTRIIIIIES"

//    fun getEntries() {
//        val entries =
//            repo.getAllEntries().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
//                if (e != null) {
//                    Log.w(TAG, "Listen failed.", e)
//                    entries.value = null
//                    return@EventListener
//                }
//
//
//            })
//    }

}