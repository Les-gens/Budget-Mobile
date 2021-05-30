package com.example.budget.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.budget.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        val chart = root.findViewById<AnyChartView>(R.id.any_chart_view)
        homeViewModel.getEntries().observe(viewLifecycleOwner, { it ->
            var entrieslist = arrayOfNulls<String>(it.size)
            var expenses = 0.0
            var revenues = 0.0
            for (i in 0 until it.size){
                if(it[i].amount < 0) expenses += it[i].amount
                else revenues += it[i].amount
            }
            expenses *= -1
            val pie: Pie = AnyChart.pie()
            val dataEntries = ArrayList<DataEntry>()

            dataEntries.add(ValueDataEntry("expenses", expenses))
            dataEntries.add(ValueDataEntry("revenues", revenues))

            pie.data(dataEntries)
            chart.setChart(pie)

        })

        return root
    }
}