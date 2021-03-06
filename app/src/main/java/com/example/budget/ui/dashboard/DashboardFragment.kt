package com.example.budget.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.budget.AddEntryActivity
import com.example.budget.R
import com.example.budget.extensions.Extensions.toast
import com.example.budget.models.EntriesModel
import com.example.budget.ui.auth.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var listView: ListView
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

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

        val logoutBtn = root.findViewById<ImageButton>(R.id.logoutButton)
        logoutBtn.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                firebaseAuth.signOut()
                val intent = Intent(context, SignUpActivity::class.java);
                startActivity(intent);
                activity?.toast("signed out")
                activity?.finish()
            }
        })

        val addEntryBtn = root.findViewById<ImageButton>(R.id.entry_add)

        addEntryBtn.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                val intent = Intent(context, AddEntryActivity::class.java);
                startActivity(intent);
                activity?.finish()
            }
        })

        listView = root.findViewById<ListView>(R.id.entries_view)
        try {
            dashboardViewModel.getEntries().observe(viewLifecycleOwner, { it ->
                val  alEntries: ArrayList<EntriesModel> = ArrayList(it.toCollection(ArrayList()))
                alEntries.sortByDescending { it.amount }

                context?.let {
                    val adapter: EntryAdapter = EntryAdapter(it, alEntries)
                    listView.adapter = adapter
                }
            })
        } catch(e: Exception) {

        }


        return root
    }



}