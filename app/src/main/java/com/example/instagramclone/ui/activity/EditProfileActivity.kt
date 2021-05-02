package com.example.instagramclone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.instagramclone.R
import com.example.instagramclone.ui.utils.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var following : EditText
    private lateinit var follower : EditText
    private lateinit var bio : EditText
    private lateinit var save : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        initViews()
        initialiseStoredInformationInViews()

        save.setOnClickListener {
            updateNewDataInPreferences()

        }
    }


    private fun updateNewDataInPreferences() {

        val newName: String = name.text.toString().trim()
        val newEmail: String = email.text.toString()
        val newPassword: String = password.text.toString()
        val newFollower: String = follower.text.toString()
        val newFollowing: String = following.text.toString()
        val newBio: String = if(bio.text.toString().trim().isEmpty()) "No bio" else bio.text.toString()

        if (newName.length > 3) {
            if (checkEmailValidUsingRegex(newEmail)) {
                if (checkPasswordValidUsingRegex(newPassword)) {
                    storeEmailAndPasswordNameBioFollowerAndFollowing(this,
                        newEmail,
                        newPassword,
                        newName,
                        newBio,
                        newFollower,
                        newFollowing
                    )
                    startActivity(Intent(this, UserProfileActivity::class.java))
                    finish()
                } else
                    Toast.makeText(this, "password is invalid", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "email is invalid", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this, "name length should greater than 3 letter",Toast.LENGTH_SHORT).show()
    }

    private fun initViews(){
        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        save = findViewById(R.id.save)
        bio = findViewById(R.id.bio)
        follower = findViewById(R.id.follower)
        following = findViewById(R.id.following)
    }

    private fun initialiseStoredInformationInViews(){
        val emailPasswordNameBioFollowerFollowing : Array<String> = getEmailPasswordNameBioFollowerAndFollowing(this)!!
        email.setText(emailPasswordNameBioFollowerFollowing[0])
        password.setText(emailPasswordNameBioFollowerFollowing[1])
        name.setText(emailPasswordNameBioFollowerFollowing[2])
        bio.setText(emailPasswordNameBioFollowerFollowing[3])
        follower.setText(emailPasswordNameBioFollowerFollowing[4])
        following.setText(emailPasswordNameBioFollowerFollowing[5])
    }

    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent(this, UserProfileActivity::class.java))
        finish()
    }
}