package com.example.instagramclone.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    var postURl : String
) : Parcelable