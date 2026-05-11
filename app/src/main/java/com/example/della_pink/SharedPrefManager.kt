package com.example.della_pink

import android.content.Context

class SharedPrefManager(context: Context) {

    private val sharedPref =
        context.getSharedPreferences(
            "user_pref",
            Context.MODE_PRIVATE
        )
    fun saveUser(
        nama: String,
        phone: String,
        username: String,
        password: String
    ) {
        sharedPref.edit()
            .putString("nama", nama)
            .putString("phone", phone)
            .putString("username", username)
            .putString("password", password)
            .apply()
    }
    fun getUsername(): String {
        return sharedPref.getString(
            "username",
            ""
        ) ?: ""
    }
    fun getPassword(): String {
        return sharedPref.getString(
            "password",
            ""
        ) ?: ""
    }
}
