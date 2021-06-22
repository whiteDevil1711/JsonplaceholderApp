package com.kuldeep.makwana.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kuldeep.makwana.R
import com.kuldeep.makwana.SharedPref
import com.kuldeep.makwana.dialog.detailsDialog
import com.kuldeep.makwana.home.adapter.ImageAdapter
import com.kuldeep.makwana.home.model.ImageModel
import com.kuldeep.makwana.home.viewModel.HomeViewModel
import com.kuldeep.makwana.login.LoginActivity
import com.kuldeep.makwana.showLogoutDialog
import com.kuldeep.makwana.showSnackBarWithoutAction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_dashboard.view.*

class HomeActivity : AppCompatActivity(), ImageAdapter.ImageAdapterClickListener,
        View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        initView()
        initListener()
    }

    private fun initView() {
        toolbar.setTitle("Home")
        toolbar.btnRight.visibility = View.VISIBLE

        includeProgress.visibility = View.VISIBLE

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        rvList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(applicationContext)
        rvList.layoutManager = layoutManager

        homeViewModel.fetchImages()

        homeViewModel.postModelListLiveData?.observe(this, {

            if (it != null) {
                rvList.visibility = View.VISIBLE
                rvList.adapter = ImageAdapter(
                        arrayList = it as ArrayList<ImageModel>,
                        context = applicationContext,
                        listener = this
                )
            } else {
                showSnackBarWithoutAction(
                        root_layout = rvList,
                        message = "Something Wrong",
                        length = Snackbar.LENGTH_SHORT
                )
            }
            includeProgress.visibility = View.GONE
        })
    }

    private fun initListener() {
        toolbar.btnRight.setOnClickListener(this)
    }

    override fun onSelectImage(position: Int, imageModel: ImageModel) {

        detailsDialog(
                activity = this,
                title = getString(R.string.are_you_sure_logout),
                leftButtonStringId = R.string.cancel,
                rightButtonStringId = R.string.okay,
                leftClickListener = null,
                rightClickListener = null,
                imageModel = imageModel
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnRight -> {
                showLogoutDialog(
                        activity = this,
                        title = getString(R.string.are_you_sure_logout),
                        description = getString(R.string.lbl_message_details),
                        leftButtonStringId = R.string.cancel,
                        rightButtonStringId = R.string.logout,
                        leftClickListener = null,
                        rightClickListener = View.OnClickListener {
                            SharedPref.clear()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                )
            }
        }
    }
}