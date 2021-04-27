package com.example.instagramclone.ui.model

data class Profile(
    val name : String = "Guest",
    val bio : String = "bio",
    val email : String,
    val thumbnail : String = "add thumbnail url",
    val followersCount : Int = 100,
    val followingCount : Int = 100,
    val postCount : Int = 10,
    val posts : ArrayList<String> = postsUrl
)

val postsUrl : ArrayList<String> = ArrayList<String>().apply {
    //add 10 urls here
}


/**
 * Demo function for reference purposes
 */
fun demo() {
    //create account k through
    val ankitKiProfile : Profile = Profile(name = "", email = "")

    //login account k through
    val abhishekKiProfile : Profile = Profile(email = "")
}