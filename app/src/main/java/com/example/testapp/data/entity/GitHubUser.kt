package com.example.testapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class GitHubUser(
    @PrimaryKey val id: Long,
    val login: String,
    val avatar_url: String
)