package com.example.budget.ui.dashboard

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.budget.R
import androidx.navigation.fragment.findNavController

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val textCurrentMonthView: TextView = root.findViewById(R.id.currentMonth)
        dashboardViewModel.currentMonth.observe(viewLifecycleOwner, Observer {
            textCurrentMonthView.text = it
        })

        val previousMonthBtn = root.findViewById<ImageButton>(R.id.previousMonthButton)
        previousMonthBtn.setOnClickListener{
            dashboardViewModel.previousMonth()
        }

        val nextMonthBtn = root.findViewById<ImageButton>(R.id.nextMonthButton)
        nextMonthBtn.setOnClickListener{
            dashboardViewModel.nextMonth()
        }

        return root
    }



}