package com.jack.sample.pixabay.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jack.baselibrary.extend.createViewModel
import com.jack.sample.pixabay.R
import com.jack.sample.pixabay.home.data.repository.MediumRepository
import com.jack.sample.pixabay.home.ui.viewcontroller.MediumSwitchController
import com.jack.sample.pixabay.home.ui.viewcontroller.MediumViewController
import com.jack.sample.pixabay.home.viewmodel.HomeViewModel
import com.jack.sample.pixabay.search.SearchActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: AppCompatActivity() {

    private val viewModel by lazy {
        createViewModel {
            HomeViewModel(MediumRepository())
        }
    }

    private var mediumSwitchController: MediumSwitchController? = null
    private var mediumViewController: MediumViewController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.mediumLiveData.observe(this, Observer { mediumData ->
            mediumData?.let {
                mediumViewController?.update(it)
            }
        })
        viewModel.start()
    }

    private fun initView() {
        mediumSwitchController = MediumSwitchController(btn_medium_display_switch) { style ->
            mediumViewController?.setLayoutStyle(style)
        }
        mediumViewController = MediumViewController(list_medium, progress_medium_loading)
        btn_medium_search.apply {
            setOnClickListener {
                SearchActivity.start(this@HomeActivity)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            val keyword = data?.getStringExtra(SearchActivity.BUNDLE_ON_RESULT_STRING_KEYWORD)
            keyword?.let {
                viewModel.searchMedium(keyword)
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