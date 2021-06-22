package com.kuldeep.makwana.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.kuldeep.makwana.*
import com.kuldeep.makwana.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 21-06-2021.
 * PS: Not easy to write code, please indicate.
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        initView()
        initListener()
    }

    private fun initView() {
        toolbar.setTitle(R.string.login)
        editEmail.setText(getString(R.string.dummyEmail))
        editPassword.setText(getString(R.string.dummyPassword))
    }

    private fun initListener() {
        btnLogin.setOnClickListener(this@LoginActivity)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> {
                hideKeyboard(activity = this)
                when {
                    editEmail.text.toString().isEmpty() -> {
                        showSnackBarWithoutAction(
                            root_layout = btnLogin,
                            message = getString(R.string.please_enter_an_email),
                            length = Snackbar.LENGTH_SHORT
                        )
                    }
                    !isValidEmail(editEmail.text.toString()) -> {
                        showSnackBarWithoutAction(
                            root_layout = btnLogin,
                            message = getString(R.string.please_enter_a_valid_email),
                            length = Snackbar.LENGTH_SHORT
                        )
                    }
                    editEmail.text.toString().trim() != getString(R.string.dummyEmail) -> {
                        showSnackBarWithoutAction(
                            root_layout = btnLogin,
                            message = getString(R.string.please_enter_a_valid_dummy_email),
                            length = Snackbar.LENGTH_SHORT
                        )
                    }
                    editPassword.text.toString().isEmpty() -> {
                        showSnackBarWithoutAction(
                            root_layout = btnLogin,
                            message = getString(R.string.please_enter_a_password),
                            length = Snackbar.LENGTH_SHORT
                        )
                    }
                    !validatePassword(editPassword.text.toString().trim()) -> {
                        showSnackBarWithoutAction(
                            root_layout = btnLogin,
                            message = getString(R.string.text_pwd_format_incorrect),
                            length = Snackbar.LENGTH_SHORT
                        )
                    }
                    editPassword.text.toString().trim() != getString(R.string.dummyPassword) -> {
                        showSnackBarWithoutAction(
                            root_layout = btnLogin,
                            message = getString(R.string.please_enter_a_valid_dummy_password),
                            length = Snackbar.LENGTH_SHORT
                        )
                    }
                    else -> {
                        SharedPref.save(Constant.EMAIL, editEmail.text.toString())
                        SharedPref.save(Constant.PASSWORD, editPassword.text.toString())
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
}