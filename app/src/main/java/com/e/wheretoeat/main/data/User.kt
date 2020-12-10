package com.e.wheretoeat.main.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val userName: String,
    val pictureUrl: String,
    val address: String,
    val phone: String,
    val email: String
):Parcelable
