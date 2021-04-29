package com.example.instagramclone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.example.instagramclone.R
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


            if (checkEmailValidUsingRegex(emailString)) {
                if (checkPasswordValidUsingRegex(passwordString)) {
                    val signInSuccessful: Boolean = true
                    onSignIn(signInSuccessful, emailString, passwordString)
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
        val credentials: Pair<String, String>? = getEmailAndPassword(this)

        if (credentials != null) {
            onSignIn(signInSuccessful = true, emailString = credentials.first, passwordString = credentials.second)
        }
    }

    private fun initViews() {
        emailBox = findViewById(R.id.email)
        passwordBox = findViewById(R.id.password)
        signInButton = findViewById(R.id.sign_in_button)
        createAccountButton = findViewById(R.id.create_account_button)
        forgotPassword = findViewById(R.id.forgot_password)
    }

    private fun onSignIn(signInSuccessful: Boolean, emailString: String, passwordString: String) {
        if (signInSuccessful) {
            try {

                storeEmailAndPassword(this, emailString, passwordString)

                var userprofile_intent : Intent = Intent(this,UserProfieActivity::class.java)

                val name: String= nameList[2]
                var bundle : Bundle = Bundle()
                bundle.putString("Username",emailString)
                bundle.putString("Name",name)
                userprofile_intent.putExtras(bundle)

                startActivity(userprofile_intent)
                finish()

            }catch (e : Exception){
                e.printStackTrace()
            }

//            home page activity has been commented
//            val openHomeActivityIntent: Intent = Intent(this, HomeActivity::class.java)
//
//            val bundle: Bundle = Bundle()
//            bundle.putString("email_text", emailString)
//            bundle.putString("password_text", passwordString)
//
//            openHomeActivityIntent.putExtras(bundle)
//
//            startActivity(openHomeActivityIntent)
//            finish()

//            Toast.makeText(this, "Welcome, Welcome!.", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Wrong credentials!!!!", Toast.LENGTH_SHORT).show()
        }
    }

}