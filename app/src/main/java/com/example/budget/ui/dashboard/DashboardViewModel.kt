package com.example.budget.ui.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DashboardViewModel : ViewModel() {

    var actual = LocalDateTime.now()

    var formatter = DateTimeFormatter.ofPattern("MMMM-yyyy")

    private val _text = MutableLiveData<String>().apply {
        value = "Gestion de budget"
    }
    val text: LiveData<String> = _text


    private val _currentMonth = MutableLiveData<String>().apply {
        var formattedDate = actual.format(formatter)
        value = formattedDate.toString()
    }

    val currentMonth: LiveData<String> = _currentMonth


    fun previousMonth(){
        actual = actual.minusMonths(1);
        _currentMonth.value = actual.format(formatter);
    }

    fun nextMonth(){
        actual = actual.plusMonths(1);
        _currentMonth.value = actual.format(formatter);
    }

}