package com.example.budget.repositories

import com.example.budget.utils.FirebaseUtils.db
import com.example.budget.utils.FirebaseUtils.firebaseUser


class EntriesRepository {
    val user = firebaseUser

    fun getAllEntries(){
        db.collection("users/${user!!.uid.toString()}/entries")
    }

}