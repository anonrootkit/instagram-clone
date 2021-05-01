package com.example.instagramclone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.instagramclone.R
import com.example.instagramclone.ui.model.Profile
import com.example.instagramclone.ui.model.getUserProfile
import com.example.instagramclone.ui.utils.*


class CreateAccountActivity : AppCompatActivity() {


    private lateinit var nameBox: EditText
    private lateinit var emailBox: EditText
    private lateinit var passwordBox: EditText
    private lateinit var createAccountButton: Button
    private lateinit var signInButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initViews()

        createAccountButton.setOnClickListener {
            val emailString: String = emailBox.text.toString()
            val passwordString: String = passwordBox.text.toString()
            val nameString: String = nameBox.text.toString()



            if (checkEmailValidUsingRegex(emailString)) {

                if (checkPasswordValidUsingRegex(passwordString)) {

                    val createAccountSuccessful: Boolean = true
                    onCreateAccount(createAccountSuccessful,emailString,passwordString,nameString)


                } else {
                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
            }
        }

        signInButton.setOnClickListener {
            val firstActivityIntent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(firstActivityIntent)
        }

    }

    private fun initViews(){
        nameBox = findViewById(R.id.name)
        emailBox = findViewById(R.id.email)
        passwordBox = findViewById(R.id.password)
        createAccountButton = findViewById(R.id.create_account_button)
        signInButton = findViewById(R.id.sign_in_button)
    }

    private fun onCreateAccount(isCreateAccountSuccessful: Boolean,emailString: String, passwordString: String,nameString:String){
        if (isCreateAccountSuccessful) {
            storeEmailAndPasswordAndName(this, emailString,passwordString,nameString)
            startActivity(Intent(this, UserProfileActivity::class.java))
            finish()
        }

    }
}