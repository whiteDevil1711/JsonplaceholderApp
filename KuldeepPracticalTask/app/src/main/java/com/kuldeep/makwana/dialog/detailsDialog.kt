package com.kuldeep.makwana.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kuldeep.makwana.R
import com.kuldeep.makwana.application.ApplicationApp.Companion.context
import com.kuldeep.makwana.home.model.ImageModel
import kotlinx.android.synthetic.main.item_list_layout.view.*

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

fun detailsDialog(
        activity: FragmentActivity?,
        title: String? = null,
        @StringRes leftButtonStringId: Int,
        @StringRes rightButtonStringId: Int,
        leftClickListener: View.OnClickListener? = null,
        rightClickListener: View.OnClickListener? = null,
        imageModel: ImageModel? = null
): Dialog? {
    return activity?.detailsDialog(
            title,
            leftButtonStringId,
            rightButtonStringId,
            leftClickListener,
            rightClickListener,
            imageModel
    )
}

@SuppressLint("SetTextI18n")
fun Activity.detailsDialog(
        title: String?,
        @StringRes leftButtonId: Int,
        @StringRes rightButtonId: Int,
        leftClickListener: View.OnClickListener?,
        rightClickListener: View.OnClickListener?,
        imageModel: ImageModel?
): Dialog {
    val (dialog, contentView) = initBaseContent()

    val txtItemId: TextView = contentView.findViewById(R.id.txtItemId)
    imageModel?.id?.let {
        txtItemId.text = "ItemId selected: $it"
        txtItemId.visibility = View.VISIBLE
    }

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

    val image: AppCompatImageView = contentView.findViewById(R.id.imgDetails)
    Glide.with(context)
            .load(imageModel?.url)
            .placeholder(R.drawable.ic_place_holder)
            .into(image)

    setContentView(dialog, contentView)
    if (!this.isFinishing) {
        dialog.show()
    }
    return dialog
}

private fun Activity.initBaseContent(): Pair<Dialog, View> {
    val dialog = createDialog(this)
    dialog.setCanceledOnTouchOutside(false)
    val contentView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_details, null)
    return Pair(dialog, contentView)
}
