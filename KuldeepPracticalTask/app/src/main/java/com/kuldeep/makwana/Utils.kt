package com.kuldeep.makwana

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 21-06-2021.
 * PS: Not easy to write code, please indicate.
 */
fun isValidEmail(target: CharSequence): Boolean {
    val emailPattern =  Patterns.EMAIL_ADDRESS

    val pattern: Pattern = Pattern.compile(emailPattern.toString())
    val matcher: Matcher = pattern.matcher(target)

    return matcher.matches()
}

fun showSnackBarWithoutAction(root_layout: View, message: String, length: Int) {
    val snackBar = Snackbar.make(
        root_layout,
        message,
        length
    )
    val snackBarView = snackBar.view
    val snackTextView =
        snackBarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
    snackTextView.maxLines = 2
    snackBar.show()
}

fun validatePassword(password: String) : Boolean
{
    val passPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^!'\"#\$%&’\\]()\\[*+,-./:;<=>?@_`{|}~\\\\]).{8,}\$"

    val pattern: Pattern = Pattern.compile(passPattern)
    val matcher: Matcher = pattern.matcher(password)

    return matcher.matches()
}

fun hideKeyboard(activity: Activity?) {
    if (activity != null) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        // Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        // If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}