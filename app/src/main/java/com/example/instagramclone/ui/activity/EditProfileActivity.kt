package com.example.instagramclone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.instagramclone.R
import com.example.instagramclone.ui.utils.checkEmailValidUsingRegex
import com.example.instagramclone.ui.utils.checkPasswordValidUsingRegex
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

        }
    }


    private fun updateNewDataInPreferences() {

        val newName: String = name.text.toString()
        val newEmail: String = email.text.toString()
        val newPassword: String = password.text.toString()

        if (newName.length > 3) {
            if (checkEmailValidUsingRegex(newEmail)) {
                if (checkPasswordValidUsingRegex(newPassword)) {
                    storeEmailAndPasswordAndName(this, newEmail, newPassword, newName)
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