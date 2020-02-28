package com.example.loginregkot.Utility

import android.content.Context
import android.content.SharedPreferences

class Constant(val context: Context) {
    val sharedPreference : SharedPreferences = context.getSharedPreferences("Mypref", Context.MODE_PRIVATE)
    val editor : SharedPreferences.Editor = sharedPreference.edit()
}