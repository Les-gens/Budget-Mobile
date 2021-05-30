package com.example.budget

import android.content.Intent
import android.os.Bundle
import com.example.budget.MainActivity
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.budget.models.EntriesModel
import com.example.budget.repositories.EntriesRepository
import com.example.budget.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.activity_add_entry.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var actual = LocalDateTime.now()

        var formatter = DateTimeFormatter.ofPattern("MM-yyyy")
        var formattedDate = actual.format(formatter)

        setContentView(R.layout.activity_add_entry)

        submit_entry.setOnClickListener {
            var title = title_form.text.toString()
            var amount: Double = amount_form.text.toString().toDouble()
            var date: String = date_form.text.toString().format(formatter)
            var dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
            saveEntryToFireBase(EntriesModel(amount, title, "", ""), date)
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
    var repository = EntriesRepository()

    fun saveEntryToFireBase(model: EntriesModel, currentDate: String) {

        repository.saveEntry(model, currentDate).addOnFailureListener {
            Log.v("FAILED_ENTRY_SAVE","Failed to save Entry!")
        }

    }
}