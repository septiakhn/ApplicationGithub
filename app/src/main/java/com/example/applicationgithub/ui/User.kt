package com.example.applicationgithub.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val avatarUrl: String,
    val following: String,
    val followers: String,
    val username: String?
):Parcelable
