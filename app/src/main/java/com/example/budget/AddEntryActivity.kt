package com.example.budget

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.budget.extensions.Extensions.toast
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
            var date: String = date_form.text.toString().substring(3)
            var day: String = date_form.text.toString().substring(0, 3)
            var depense: Boolean = depense_switch.isChecked
            if (depense == true){
                amount =amount * -1
            }
            var dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
            saveEntryToFireBase(EntriesModel(amount, title, "", day), date)
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
    var repository = EntriesRepository()

    private fun vibratePhone() {

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }

    fun saveEntryToFireBase(model: EntriesModel, currentDate: String) {

        repository.saveEntry(model, currentDate).addOnFailureListener {
            Log.v("FAILED_ENTRY_SAVE","Failed to save Entry!")
        }.addOnSuccessListener {
            toast("created entry successfully")
            vibratePhone()

        }

    }
}