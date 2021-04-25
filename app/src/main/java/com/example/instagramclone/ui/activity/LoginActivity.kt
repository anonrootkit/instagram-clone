package com.example.instagramclone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.example.instagramclone.R
import com.example.instagramclone.ui.utils.checkEmailValidUsingRegex
import com.example.instagramclone.ui.utils.checkPasswordValidUsingRegex
import com.example.instagramclone.ui.utils.getEmailAndPassword
import com.example.instagramclone.ui.utils.storeEmailAndPassword

class LoginActivity : AppCompatActivity() {


    private lateinit var emailBox : EditText
    private lateinit var passwordBox : EditText
    private lateinit var signInButton : Button
    private lateinit var createAccountButton: Button
    private lateinit var forgotPassword : TextView
    private lateinit var signInSwitch : SwitchCompat
    private lateinit var thumbnail : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()

        checkIfUserAlreadyLoggedIn()

        signInButton.setOnClickListener {
            val emailString : String = emailBox.text.toString()
            val passwordString : String = passwordBox.text.toString()
            val areYouHuman : Boolean = signInSwitch.isChecked


            if (areYouHuman){
                if (checkEmailValidUsingRegex(emailString)){
                    if (checkPasswordValidUsingRegex(passwordString)){
                        val signInSuccessful : Boolean = true
                        onSignIn(signInSuccessful, emailString, passwordString)
                    }else{
                        Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please join being human!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkIfUserAlreadyLoggedIn() {
        val credentials : Pair<String, String>? = getEmailAndPassword(this)

        if (credentials != null){
            onSignIn(signInSuccessful = true, emailString = credentials.first, passwordString = credentials.second)
        }
    }

    private fun initViews(){
        emailBox = findViewById(R.id.email)
        passwordBox = findViewById(R.id.password)
        signInButton = findViewById(R.id.sign_in_button)
        createAccountButton = findViewById(R.id.create_account_button)
        forgotPassword = findViewById(R.id.forgot_password)
        signInSwitch = findViewById(R.id.sign_in_switch)
        thumbnail = findViewById(R.id.thumbnail)
    }

    private fun onSignIn(signInSuccessful : Boolean, emailString : String, passwordString : String) {
        if (signInSuccessful){
            storeEmailAndPassword(this, emailString, passwordString)

            val openHomeActivityIntent : Intent = Intent(this, HomeActivity::class.java)

            val bundle : Bundle = Bundle()
            bundle.putString("email_text", emailString)
            bundle.putString("password_text", passwordString)

            openHomeActivityIntent.putExtras(bundle)

            startActivity(openHomeActivityIntent)
            finish()

            Toast.makeText(this, "Welcome, Welcome!.", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this, "Wrong credentials!!!!", Toast.LENGTH_SHORT).show()
        }
    }
}