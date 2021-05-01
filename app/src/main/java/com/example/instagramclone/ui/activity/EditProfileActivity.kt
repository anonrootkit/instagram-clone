package com.example.instagramclone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.instagramclone.R
import com.example.instagramclone.ui.utils.getEmailAndPasswordAndName
import com.example.instagramclone.ui.utils.storeEmailAndPasswordAndName

class EditProfileActivity : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var save : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        initViews()
        initialiseStoredInformationInViews()

        save.setOnClickListener {
            updateNewDataInPreferences()
            startActivity(Intent(this, UserProfileActivity::class.java))
            finish()
        }
    }

    private fun updateNewDataInPreferences() {
        val newName : String = name.text.toString()
        val newEmail : String = email.text.toString()
        val newPassword : String = password.text.toString()

        storeEmailAndPasswordAndName(this, newEmail, newPassword, newName)
    }

    private fun initViews(){
        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        save = findViewById(R.id.save)
    }

    private fun initialiseStoredInformationInViews(){
        val emailPasswordName : Triple<String, String, String> = getEmailAndPasswordAndName(this)!!
        email.setText(emailPasswordName.first)
        password.setText(emailPasswordName.second)
        name.setText(emailPasswordName.third)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent(this, UserProfileActivity::class.java))
        finish()
    }
}