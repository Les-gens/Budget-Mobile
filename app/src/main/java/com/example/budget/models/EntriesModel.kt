package com.example.budget.models

import java.io.Serializable


class EntriesModel (var amount: Double, var title: String, Tag: String, var date: String): Serializable{
    constructor():this(0.0,"Poet","Poet", "18/11/1999")
}