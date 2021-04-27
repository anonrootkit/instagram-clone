package com.example.instagramclone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.instagramclone.R
import com.example.instagramclone.ui.utils.getEmailAndPassword
import com.example.instagramclone.ui.utils.languagelist
import kotlin.math.log

class StartingpageActivity : AppCompatActivity() {

    private lateinit var createraccount : Button
    private lateinit var login : TextView
    private lateinit var language_spinner : Spinner



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startingpage)

        checkIfUserAlreadyLoggedIn()

        // initalise views


        createraccount = findViewById(R.id.createaccount)
        login = findViewById(R.id.login)
        language_spinner = findViewById(R.id.language_spinner)

        val spinnerAdapter :ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, languagelist)
        language_spinner.adapter=spinnerAdapter

        language_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
                val selectedLanguage = languagelist[position]
                Toast.makeText(this@StartingpageActivity ,"$selectedLanguage", Toast.LENGTH_SHORT).show()


            }

        }


        createraccount.setOnClickListener {
            val createraccountIntent : Intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(createraccountIntent)
            finish()
        }

        login.setOnClickListener {
            val loginIntent : Intent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }



    }
    private fun checkIfUserAlreadyLoggedIn() {
        val credentials: Pair<String, String>? = getEmailAndPassword(this)

        if (credentials != null) {
            val intentForHomeActivity : Intent = Intent(this, HomeActivity::class.java)
            startActivity(intentForHomeActivity)
            finish()
        }

    }



}