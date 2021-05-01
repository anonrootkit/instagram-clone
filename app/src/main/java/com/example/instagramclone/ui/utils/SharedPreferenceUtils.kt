@file:Suppress("DEPRECATION")

package com.example.instagramclone.ui.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val EMAIL_KEY = "EMAIL_KEY"
private const val PASSWORD_KEY = "PASSWORD_KEY"
private const val NAME_KEY = "NAME_KEY"
private const val BIO_KEY = "BIO_KEY"


private fun getDefaultSharedPreferences(context: Context) : SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

private fun getEditableDefaultSharedPreferences(context: Context) : SharedPreferences.Editor {
    return PreferenceManager.getDefaultSharedPreferences(context).edit()
}

fun storeEmailAndPassword(
    context: Context,
    emailString : String,
    passwordString : String
){
    val editablePreferences = getEditableDefaultSharedPreferences(context = context)
    editablePreferences.putString(EMAIL_KEY, emailString)
    editablePreferences.putString(PASSWORD_KEY, passwordString)
    editablePreferences.apply()
}
fun storeEmailAndPasswordAndNameAndBio(
    context: Context,
    emailString : String,
    passwordString : String,
    nameString : String,
    bioString: String
){
    val editablePreferences = getEditableDefaultSharedPreferences(context = context)
    editablePreferences.putString(EMAIL_KEY, emailString)
    editablePreferences.putString(PASSWORD_KEY, passwordString)
    editablePreferences.putString(NAME_KEY, nameString)
    editablePreferences.putString(BIO_KEY, bioString)

    editablePreferences.apply()
}

fun getEmailAndPasswordAndName(context: Context) : Triple<String, String, String>? {
    val sharedPreferences = getDefaultSharedPreferences(context)

    val emailString : String = sharedPreferences.getString(EMAIL_KEY, null) ?: return null
    val passwordString : String = sharedPreferences.getString(PASSWORD_KEY, null) ?: return null
    val nameString : String = sharedPreferences.getString(NAME_KEY, "Guest") ?: return null

    return Triple(emailString, passwordString, nameString)
}

fun getEmailAndPasswordAndNameAndBio(context: Context) : Array<String>? {
    val sharedPreferences = getDefaultSharedPreferences(context)

    val emailString : String = sharedPreferences.getString(EMAIL_KEY, null) ?: return null
    val passwordString : String = sharedPreferences.getString(PASSWORD_KEY, null) ?: return null
    val nameString : String = sharedPreferences.getString(NAME_KEY, "Guest") ?: return null
    val bioString: String = sharedPreferences.getString(BIO_KEY,"No bio") ?: return null

    return arrayOf(emailString,passwordString,nameString,bioString)

}



