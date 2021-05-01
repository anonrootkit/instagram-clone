package com.example.instagramclone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.instagramclone.R
import com.example.instagramclone.ui.model.Profile
import com.example.instagramclone.ui.model.getUserProfile
import com.example.instagramclone.ui.utils.*

class LoginActivity : AppCompatActivity() {


    private lateinit var emailBox: EditText
    private lateinit var passwordBox: EditText
    private lateinit var signInButton: Button
    private lateinit var createAccountButton: TextView
    private lateinit var forgotPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()

        checkIfUserAlreadyLoggedIn()

        signInButton.setOnClickListener {
            val emailString: String = emailBox.text.toString()
            val passwordString: String = passwordBox.text.toString()
            val nameString : String = "Guest"
            val bioString : String = "No bio"


            if (checkEmailValidUsingRegex(emailString)) {
                if (checkPasswordValidUsingRegex(passwordString)) {
                    val signInSuccessful: Boolean = true
                    onSignIn(signInSuccessful, emailString, passwordString,nameString,bioString)
                } else {
                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
            }
        }


        createAccountButton.setOnClickListener {
            var intent: Intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)

        }

    }


    private fun checkIfUserAlreadyLoggedIn() {
        val credentials: Array<String>? = getEmailAndPasswordAndNameAndBio(this)

        if (credentials != null) {
            onSignIn(signInSuccessful = true, emailString = credentials[0], passwordString = credentials[1],nameString = credentials[2],bioString = credentials[3])
        }
    }

    private fun initViews() {
        emailBox = findViewById(R.id.email)
        passwordBox = findViewById(R.id.password)
        signInButton = findViewById(R.id.sign_in_button)
        createAccountButton = findViewById(R.id.create_account_button)
        forgotPassword = findViewById(R.id.forgot_password)
    }

    private fun onSignIn(
        signInSuccessful: Boolean,
        emailString: String,
        passwordString: String,
        nameString: String,
        bioString: String
    ) {
        if (signInSuccessful) {
            storeEmailAndPasswordAndNameAndBio(this, emailString, passwordString,nameString,bioString)
            startActivity(Intent(this,UserProfileActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Wrong credentials!!!!", Toast.LENGTH_SHORT).show()
        }
    }

}