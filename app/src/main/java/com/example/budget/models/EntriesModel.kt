package com.example.budget.models

import java.util.*
import java.io.Serializable


class EntriesModel (var amount: Double, var title: String, Tag: String, var date: Date): Serializable{
    constructor():this(0.0,"Poet","Poet", Date("18/11/1999"))
}