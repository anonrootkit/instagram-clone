package com.example.instagramclone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.instagramclone.R
import com.example.instagramclone.ui.model.Profile
import com.example.instagramclone.ui.utils.ArrayAdapter_GridView
import com.example.instagramclone.ui.utils.getEmailAndPasswordAndName
import com.example.instagramclone.ui.utils.post
import com.example.instagramclone.ui.utils.postList

class UserProfileActivity : AppCompatActivity() {

    private lateinit var username: TextView
    private lateinit var userPic: ImageView
    private lateinit var name: TextView
    private lateinit var bio: TextView
    private lateinit var post_in_gridview: GridView
    private lateinit var postCount: TextView
    private lateinit var followersCount: TextView
    private lateinit var followingCount: TextView
    private lateinit var editProfile : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profie)
        initaliseView()


        val emailPasswordName : Triple<String, String, String> = getEmailAndPasswordAndName(this)!!
        val profile = Profile(name = emailPasswordName.third, email = emailPasswordName.first)

        updateViewsData(profile)

        val postsAdapter = ArrayAdapter_GridView(this, profile.postList)
        post_in_gridview.adapter = postsAdapter

        editProfile.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
            finish()
        }
    }

    private fun updateViewsData(profile: Profile) {
        username.text = profile.email
        name.text = profile.name
        bio.text = profile.bio
        postCount.text = "${profile.postCount}"
        followingCount.text = "${profile.followingCount}"
        followersCount.text = "${profile.followersCount}"

        Glide.with(userPic).load(profile.thumbnail).apply(RequestOptions.circleCropTransform()).into(userPic)
    }

    private fun initaliseView() {
        username = findViewById(R.id.username_userProfile)
        userPic = findViewById(R.id.userPic_userProfile)
        name = findViewById(R.id.name_userProfile)
        bio = findViewById(R.id.bio_userProfile)
        post_in_gridview = findViewById(R.id.gridView_userProfile)
        postCount = findViewById(R.id.post_count)
        followingCount = findViewById(R.id.following_count)
        followersCount = findViewById(R.id.followers_count)
        editProfile = findViewById(R.id.editButton_userProfile)
    }
}

