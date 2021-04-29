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
//    private lateinit var statesSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initViews()

        createAccountButton.setOnClickListener {
            val emailString: String = emailBox.text.toString()
            val passwordString: String = passwordBox.text.toString()


            if (checkEmailValidUsingRegex(emailString)) {

                if (checkPasswordValidUsingRegex(passwordString)) {

                    val createAccountSuccessful: Boolean = true
                    onCreateAccount(createAccountSuccessful,emailString,passwordString)


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
//        statesSpinner = findViewById(R.id.spinner)

//        initialiseSpinner(
//            context = this,
//            spinner = statesSpinner,
//            values = statesList,
//            functionOnItemSelected = { position ->
//                Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
//            }
//        )


    }

    private fun onCreateAccount(isCreateAccountSuccessful: Boolean,emailString: String, passwordString: String){
        if (isCreateAccountSuccessful) {
            storeEmailAndPassword(this, emailString,passwordString)

//                        val openHomeActivityIntent: Intent = Intent(this, HomeActivity::class.java)
//
//                        val bundle: Bundle = Bundle()
//                        bundle.putString("email_text", emailString)
//                        bundle.putString("password_text", passwordString)
//
//                        openHomeActivityIntent.putExtras(bundle)
//
//                        startActivity(openHomeActivityIntent)

            val userprofile_intent : Intent = Intent(this, UserProfileActivity::class.java)
            val nameString : String = nameBox.text.toString()
            val profile : Profile = getUserProfile(nameString,emailString)

            var bundle : Bundle = Bundle()
            bundle.putParcelable("USER_PROFILE",profile)

            userprofile_intent.putExtras(bundle)

            startActivity(userprofile_intent)
            finish()

            Toast.makeText(this, "Welcome, Welcome!", Toast.LENGTH_SHORT).show()

        }

    }
}