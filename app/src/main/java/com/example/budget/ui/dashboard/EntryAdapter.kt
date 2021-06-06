package com.example.budget.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.budget.R
import com.example.budget.models.EntriesModel
import java.util.ArrayList

class EntryAdapter(private val context: Context, private val arrayList: ArrayList<EntriesModel>, ) : BaseAdapter() {
    private lateinit var title: TextView
    private lateinit var amount: TextView

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.entry_row, parent, false)
        if(convertView != null) {
            title = convertView.findViewById(R.id.entry_title)
            amount = if (arrayList[position].amount > 0) {
                convertView.findViewById(R.id.income_amount)
            } else {
                convertView.findViewById(R.id.expense_amount)
            }
        }
        title.text = arrayList[position].title
        amount.text = arrayList[position].amount.toString()
        return convertView
    }

}