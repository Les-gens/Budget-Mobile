package com.example.budget.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budget.models.EntriesModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.example.budget.repositories.EntriesRepository
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot


class DashboardViewModel : ViewModel() {

    val TAG = "FIREBASE_ENTRY_VIEW_MDL"

    var actual = LocalDateTime.now()

    var formatter = DateTimeFormatter.ofPattern("MM-yyyy")

    private val _text = MutableLiveData<String>().apply {
        value = "Gestion de budget"
    }

    val text: LiveData<String> = _text

    var repository = EntriesRepository()

    var savedEntries : MutableLiveData<List<EntriesModel>> = MutableLiveData()

    private val _currentMonth = MutableLiveData<String>().apply {
        var formattedDate = actual.format(formatter)
        value = formattedDate.toString()
    }

    val currentMonth: LiveData<String> = _currentMonth


    fun previousMonth(){
        actual = actual.minusMonths(1);
        _currentMonth.value = actual.format(formatter);
        getEntries()
    }

    fun nextMonth(){
        actual = actual.plusMonths(1);
        _currentMonth.value = actual.format(formatter);
        getEntries()
    }

    fun saveEntryToFireBase(model: EntriesModel) {

        repository.saveEntry(model, _currentMonth.toString()).addOnFailureListener {
            Log.v(TAG,"Failed to save Entry!")
        }

    }

    fun getEntries() : LiveData<List<EntriesModel>> {

        try {
            repository.getAllEntries(_currentMonth.value.toString()).addSnapshotListener(
                EventListener<QuerySnapshot> { value, e ->
                    if (e != null) {
                        Log.v(TAG, "Listen failed.", e)
                        savedEntries.value = null
                        return@EventListener
                    }

                    var entriesList: MutableList<EntriesModel> = mutableListOf()
                    for (doc in value!!) {
                        var entryItem = doc.toObject(EntriesModel::class.java)
                        entriesList.add(entryItem)
                    }
                    savedEntries.value = entriesList
                })
        } catch(e : Exception) {

        }

        return savedEntries
    }



}