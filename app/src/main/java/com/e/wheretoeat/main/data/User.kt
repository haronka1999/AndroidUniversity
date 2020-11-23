package com.e.wheretoeat.main.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val userID: Int,
    val userName: String,
    val password: String
) {


}
