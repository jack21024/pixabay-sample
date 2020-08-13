package com.jack.sample.pixabay.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jack.baselibrary.extend.createViewModel
import com.jack.sample.pixabay.R
import com.jack.sample.pixabay.base.pref.GlobalPref
import com.jack.sample.pixabay.search.data.repostitory.LocalSearchDataSource
import com.jack.sample.pixabay.search.data.repostitory.SearchRepository
import com.jack.sample.pixabay.search.ui.viewcontroller.SearchHistoryViewController
import com.jack.sample.pixabay.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val viewModel by lazy {
        createViewModel {
            SearchViewModel(
                SearchRepository(
                    LocalSearchDataSource(GlobalPref)
                )
            )
        }
    }

    private var historyViewController: SearchHistoryViewController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
        initViewModel()
    }

    private fun initView() {
        historyViewController = SearchHistoryViewController(list_search_history) {
            performSearch(it)
        }
        edit_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(edit_search.text.toString().trim())
                true
            } else {
                false
            }
        }
    }

    private fun initViewModel() {
        viewModel.historyListData.observe(this, Observer {
            it.value?.let {  data ->
                historyViewController?.update(data)
            }
        })
        viewModel.start()
    }

    private fun performSearch(keyword: String) {
        if (keyword.isNotEmpty()) {
            viewModel.saveKeyword(keyword)
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(BUNDLE_ON_RESULT_STRING_KEYWORD, keyword)
            })
            finish()
        }
    }

    companion object {
        const val BUNDLE_ON_RESULT_STRING_KEYWORD = "BUNDLE_STRING_SEARCH_KEYWORD"

        fun start(activity: Activity, requestCode: Int = 0) {
            val intent = Intent(activity, SearchActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }
}