package com.example.cmtvapp.utils

import android.util.Log

object UtilMethods {

    fun printD(message: String = "message_debug") {
        Log.d("**BK-LOG-DEBUG**", message)
    }

    fun printE(message: String = "message_error") {
        Log.e("**BK-LOG-ERROR**", message)
    }

    fun printI(message: String = "message_info") {
        Log.i("**BK-LOG-INFO**", message)
    }

}