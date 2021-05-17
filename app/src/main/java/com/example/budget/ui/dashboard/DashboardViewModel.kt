package com.example.budget.ui.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Gestion de budget"
    }
    val text: LiveData<String> = _text

    //TODO
    @RequiresApi(Build.VERSION_CODES.O)
    private val _currentMonth = MutableLiveData<String>().apply {
        val current = LocalDateTime.now()
        var formatter = DateTimeFormatter.ofPattern("MMMM-yyyy")
        var formattedDate = current.format(formatter)

        value = formattedDate.toString()
    }
    //TODO
    @RequiresApi(Build.VERSION_CODES.O)
    val currentMonth: LiveData<String> = _currentMonth
}