package com.example.instagramclone.ui.model

import android.os.Parcelable
import com.example.instagramclone.ui.utils.post

@kotlinx.parcelize.Parcelize
data class Profile(
    val name : String = "Guest",
    val bio : String = "bio",
    val email : String,
    val thumbnail : String = "data:image/jpeg;base64,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",
    val followersCount : Int = 100,
    val followingCount : Int = 100,
    val postCount : Int = 10,
    val postList : ArrayList<Post> = post
) : Parcelable

/**
 * Demo functions for reference purposes
 */

fun demo() {
    //create account k through
    val ankitKiProfile : Profile = Profile(name = "", email = "")

    //login account k through
    val abhishekKiProfile : Profile = Profile(email = "")
}

fun getUserProfile(name: String, email: String) : Profile {
    return Profile(name = name, email = email)
}

fun getUserProfile(email: String) : Profile {
    return Profile(email = email)
}