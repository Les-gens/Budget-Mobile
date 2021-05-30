package com.example.budget.ui.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.budget.R

class EntryFragment : Fragment() {

    private lateinit var entryViewModel: EntryViewModel

    companion object {

        fun newInstance(): EntryFragment {
            return EntryFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        entryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_entry, container, false)
        val entryTitle: TextView = root.findViewById(R.id.entry_title)
        val entryTag: TextView = root.findViewById(R.id.entry_tag)
        val incomeAmount: TextView = root.findViewById(R.id.income_amount)
        val expenseAmount: TextView = root.findViewById(R.id.expense_amount)

        entryViewModel.text1.observe(viewLifecycleOwner, Observer {
            entryTitle.text = it
        })
        entryViewModel.text2.observe(viewLifecycleOwner, Observer {
            entryTag.text = it
        })
        entryViewModel.text3.observe(viewLifecycleOwner, Observer {
            incomeAmount.text = it
        })
        entryViewModel.text4.observe(viewLifecycleOwner, Observer {
            expenseAmount.text = it
        })


        return root
    }
}