package com.e.wheretoeat.main.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val userName: String,
    val pictureUrl: String,
    val address: String,
    val phone: String,
    val email: String
)
