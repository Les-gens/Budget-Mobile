package com.example.budget.ui.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DashboardViewModel : ViewModel() {
    //TODO
    @RequiresApi(Build.VERSION_CODES.O)
    var actual = LocalDateTime.now()
    //TODO
    @RequiresApi(Build.VERSION_CODES.O)
    var formatter = DateTimeFormatter.ofPattern("MMMM-yyyy")

    private val _text = MutableLiveData<String>().apply {
        value = "Gestion de budget"
    }
    val text: LiveData<String> = _text

    //TODO
    @RequiresApi(Build.VERSION_CODES.O)
    private val _currentMonth = MutableLiveData<String>().apply {
        var formattedDate = actual.format(formatter)
        value = formattedDate.toString()
    }
    //TODO
    @RequiresApi(Build.VERSION_CODES.O)
    val currentMonth: LiveData<String> = _currentMonth

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonth(){
        actual = actual.minusMonths(1);
        _currentMonth.value = actual.format(formatter);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonth(){
        actual = actual.plusMonths(1);
        _currentMonth.value = actual.format(formatter);
    }

}