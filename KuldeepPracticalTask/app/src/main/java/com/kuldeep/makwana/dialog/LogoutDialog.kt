package com.kuldeep.makwana

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentActivity

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 11-05-2021.
 * PS: Not easy to write code, please indicate.
 */
private const val DIALOG_WIDTH_DELTA_7 = 0.7f

private fun createDialog(activity: Activity): Dialog {
    val dialog = Dialog(activity)

    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    if (dialog.window != null) {
        dialog.window!!
                .decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
    }
    return dialog
}

private fun setContentView(dialog: Dialog, contentView: View) {
    dialog.setContentView(contentView)
    val window = dialog.window
    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val resources = dialog.context
            .resources
    val params = contentView.layoutParams as FrameLayout.LayoutParams
    params.width = ((resources.displayMetrics.widthPixels * DIALOG_WIDTH_DELTA_7).toInt())
    contentView.layoutParams = params
}
fun showLogoutDialog(
        activity: FragmentActivity?,
        title: String? = null,
        description: String? = null,
        @StringRes leftButtonStringId: Int,
        @StringRes rightButtonStringId: Int,
        leftClickListener: View.OnClickListener? = null,
        rightClickListener: View.OnClickListener? = null
): Dialog? {
    return activity?.showLogoutDialog(
            title,
            description,
            leftButtonStringId,
            rightButtonStringId,
            leftClickListener,
            rightClickListener
    )
}

fun Activity.showLogoutDialog(
        title: String?,
        description: String?,
        @StringRes leftButtonId: Int,
        @StringRes rightButtonId: Int,
        leftClickListener: View.OnClickListener?,
        rightClickListener: View.OnClickListener?
): Dialog {
    val (dialog, contentView) = initBaseContent(title, description)

    val btnLeft: AppCompatButton = contentView.findViewById(R.id.btnLeft)
    btnLeft.setText(leftButtonId)
    btnLeft.setOnClickListener {
        dialog.dismiss()
        leftClickListener?.onClick(it)
    }

    val btnRight: AppCompatButton = contentView.findViewById(R.id.btnRight)
    btnRight.setText(rightButtonId)
    btnRight.setOnClickListener {
        dialog.dismiss()
        rightClickListener?.onClick(it)
    }

    setContentView(dialog, contentView)
    if (!this.isFinishing) {
        dialog.show()
    }
    return dialog
}

private fun Activity.initBaseContent(
        title: String?,
        description: String?
): Pair<Dialog, View> {
    val dialog = createDialog(this)
    dialog.setCanceledOnTouchOutside(false)
    val contentView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_two_button, null)

    val tvTitle: TextView = contentView.findViewById(R.id.tvTitle)
    title?.let {
        tvTitle.text = it
        tvTitle.visibility = View.VISIBLE
    }

    val tvDescription: TextView = contentView.findViewById(R.id.tvDescription)
    description?.let {
        tvDescription.text = it
        tvDescription.visibility = View.VISIBLE
    }
    return Pair(dialog, contentView)
}
