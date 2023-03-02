package com.example.testapp.presentation.models

import java.util.*

data class TaskItem(
    var id: Long,
    var text: String,
    var date: Date?
)
