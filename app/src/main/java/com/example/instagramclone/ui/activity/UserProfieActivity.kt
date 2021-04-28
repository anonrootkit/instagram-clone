package com.example.instagramclone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.instagramclone.R
import com.example.instagramclone.ui.utils.ArrayAdapter_GridView
import com.example.instagramclone.ui.utils.post
import com.example.instagramclone.ui.utils.postList

class UserProfieActivity : AppCompatActivity() {

    private lateinit var username : TextView
    private lateinit var userPic : ImageView
    private lateinit var name : TextView
    private lateinit var bio : TextView
    private lateinit var post_in_gridview : GridView

//    private lateinit var followingButton : Button
//    private lateinit var messageButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profie)
        initaliseView()

        val information_from_loginpage : Bundle =intent.extras!!
        val emailString:String =information_from_loginpage.getString("Username")!!

        username.text=emailString

        var adapter_obj : ArrayAdapter_GridView = ArrayAdapter_GridView(this, post)
        post_in_gridview.adapter=adapter_obj

    }

    private fun initaliseView(){
        username=findViewById(R.id.username_userProfile)
        userPic=findViewById(R.id.userPic_userProfile)
        name=findViewById(R.id.name_userProfile)
        bio=findViewById(R.id.bio_userProfile)
        post_in_gridview=findViewById(R.id.gridView_userProfile)


    }
}

