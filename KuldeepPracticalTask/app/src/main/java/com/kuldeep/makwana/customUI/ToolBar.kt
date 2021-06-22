@file:Suppress("DEPRECATION")

package com.kuldeep.makwana.customUI

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.kuldeep.makwana.R
import kotlinx.android.synthetic.main.toolbar_dashboard.view.*

class ToolBar : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_dashboard, this, true)
    }



    fun setBackNavigationIcon(@DrawableRes resId: Int?) {
        btnRight.setImageResource(resId!!)
    }

    fun setBackground(@DrawableRes resId: Int?) {
        toolbar.setBackgroundResource(resId!!)
    }

    fun setTitle(title: String) {
        txt_toolbar_title.text = title
    }

    fun setTitle(@StringRes resId: Int) {
        txt_toolbar_title.setText(resId)
    }

    fun setNavigationIcon(@DrawableRes resId: Int) {
        btnRight.setImageResource(resId)
        btnRight.visibility = View.VISIBLE
    }

    fun setNavigationClick(listener: OnClickListener) {
        btnRight.setOnClickListener(listener)
    }

    fun setNavigationIconAndClick(@DrawableRes resId: Int, clickListener: OnClickListener) {
        btnRight.setImageResource(resId)
        btnRight.setOnClickListener(clickListener)
        btnRight.visibility = View.VISIBLE
    }

    fun hideNavigationIcons() {
        btnRight.visibility = View.GONE
    }

    fun showNavigationIcons() {
        btnRight.visibility = View.VISIBLE
    }

    fun showBackButton() {
        btnRight.visibility = View.VISIBLE
    }


    fun setTitleColor(@ColorRes resId: Int) {
        txt_toolbar_title.setTextColor(resources.getColor(resId))
    }

    fun setBackButtonIcon(@DrawableRes resId: Int) {
        btnRight.setImageResource(resId)
    }

    fun setBackButtonVisibility(visibility: Int) {
        btnRight.visibility = visibility
    }

}