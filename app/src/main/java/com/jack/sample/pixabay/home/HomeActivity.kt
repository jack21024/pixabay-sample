package com.jack.sample.pixabay.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jack.baselibrary.extend.createViewModel
import com.jack.sample.pixabay.R
import com.jack.sample.pixabay.home.data.repository.MediumRepository
import com.jack.sample.pixabay.home.enums.MediumLayoutStyle
import com.jack.sample.pixabay.home.ui.viewcontroller.MediumViewController
import com.jack.sample.pixabay.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: AppCompatActivity() {

    private val viewModel by lazy {
        createViewModel {
            HomeViewModel(MediumRepository())
        }
    }

    private var mediumViewController: MediumViewController? = null
    private var isEnableGridLayout = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        setupObservable()

    }

    private fun setupObservable() {
//        MediumRepository().getImageList().observe(this, Observer {
//            mediumViewController?.update(it)
//        })
        MediumRepository().getImageList()
        viewModel.mediumLiveData.observe(this, Observer { mediumData ->
            mediumData?.let {
                mediumViewController?.update(it)
            }
        })
        viewModel.start()
    }

    private fun initView() {
        mediumViewController = MediumViewController(list_medium)
        btn_medium_display_switch.apply {
            setOnClickListener {
                isEnableGridLayout = isEnableGridLayout.not()
                val layoutStyle = if(isEnableGridLayout) MediumLayoutStyle.GRID else MediumLayoutStyle.LIST
                mediumViewController?.setLayoutStyle(layoutStyle)
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}