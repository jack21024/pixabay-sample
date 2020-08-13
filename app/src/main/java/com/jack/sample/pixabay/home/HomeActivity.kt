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
import com.jack.sample.pixabay.home.enums.MediumLayoutStyle
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

        val style = intent.getSerializableExtra(BUNDLE_OBJ_MEDIUM_LAYOUT_STYLE)?.let {
            it as MediumLayoutStyle
        } ?: MediumLayoutStyle.LIST

        initView(style)
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

    private fun initView(style: MediumLayoutStyle?) {
        mediumViewController = MediumViewController(list_medium, progress_medium_loading)
        mediumSwitchController = MediumSwitchController(btn_medium_display_switch, style) { style ->
            mediumViewController?.setLayoutStyle(style)
        }
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
        const val BUNDLE_OBJ_MEDIUM_LAYOUT_STYLE = "BUNDLE_OBJ_MEDIUM_LAYOUT_STYLE"

        fun start(context: Context, mediaLayoutStyle: MediumLayoutStyle? = null) {
            val intent = Intent(context, HomeActivity::class.java)
            mediaLayoutStyle?.let {
                intent.putExtra(BUNDLE_OBJ_MEDIUM_LAYOUT_STYLE, mediaLayoutStyle)
            }
            context.startActivity(intent)
        }
    }
}