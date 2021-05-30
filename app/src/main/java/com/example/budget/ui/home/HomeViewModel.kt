package com.example.budget.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budget.models.EntriesModel
import com.example.budget.repositories.EntriesRepository
import com.google.firebase.firestore.QuerySnapshot
import java.util.*
import com.google.firebase.firestore.EventListener


class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val repo = EntriesRepository()
    var entries: MutableLiveData<List<EntriesModel>> = MutableLiveData()
    val TAG = "ENTRIIIIIES"

    var repository = EntriesRepository()

    var savedEntries : MutableLiveData<List<EntriesModel>> = MutableLiveData()

    fun getEntries() : LiveData<List<EntriesModel>> {

        repository.getAllEntries().addSnapshotListener(
            EventListener<QuerySnapshot> { value, e ->
                if (e != null) {
                    Log.v(TAG, "Listen failed.", e)
                    savedEntries.value = null
                    return@EventListener
                }

                var entriesList : MutableList<EntriesModel> = mutableListOf()
                for (doc in value!!) {
                    var entryItem = doc.toObject(EntriesModel::class.java)
                    entriesList.add(entryItem)
                }
                savedEntries.value = entriesList
            })

        return savedEntries
    }




}