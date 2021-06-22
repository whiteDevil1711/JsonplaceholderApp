package com.kuldeep.makwana.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.kuldeep.makwana.SharedPref

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 22-06-2021.
 * PS: Not easy to write code, please indicate.
 */
class ApplicationApp: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
        context = applicationContext
    }
}