package com.compose.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phoneNumber: String,
    val email: String
)