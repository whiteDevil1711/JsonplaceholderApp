package com.kuldeep.makwana.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kuldeep.makwana.Constant
import com.kuldeep.makwana.SharedPref
import com.kuldeep.makwana.home.HomeActivity
import com.kuldeep.makwana.login.LoginActivity

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 21-06-2021.
 * PS: Not easy to write code, please indicate.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (SharedPref.getString(Constant.EMAIL, "")!!
                .isNotEmpty() && SharedPref.getString(Constant.PASSWORD, "")!!.isNotEmpty()
        ) {
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        } else {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }
        finish()
    }
}