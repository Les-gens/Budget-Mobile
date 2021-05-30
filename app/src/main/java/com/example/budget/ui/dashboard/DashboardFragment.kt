package com.example.budget.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.budget.R
import com.example.budget.extensions.Extensions.toast
import com.example.budget.ui.auth.SignUpActivity
import com.example.budget.utils.FirebaseUtils.firebaseAuth


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

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



        return root
    }



}