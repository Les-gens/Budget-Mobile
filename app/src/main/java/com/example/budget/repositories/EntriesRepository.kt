package com.example.budget.repositories

import com.example.budget.models.EntriesModel
import com.example.budget.utils.FirebaseUtils.db
import com.example.budget.utils.FirebaseUtils.firebaseUser
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class EntriesRepository {
    val user = firebaseUser
    var actual = LocalDateTime.now()
    var formatter = DateTimeFormatter.ofPattern("MM-yyyy")
    var formattedDate = actual.format(formatter)

    fun getAllEntries(date: String = formattedDate) : CollectionReference {
        return db.collection("users/${user!!.uid.toString()}/${date}")
    }

    fun saveEntry(model: EntriesModel, date: String = formattedDate) : Task<Void> {
        val documentReference = db.collection("users").document(user!!.uid.toString())
            .collection(date).document()
        return documentReference.set(model)
    }

}