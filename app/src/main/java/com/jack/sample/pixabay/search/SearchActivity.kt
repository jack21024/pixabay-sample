package com.jack.sample.pixabay.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.jack.sample.pixabay.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    private fun initView() {
        edit_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(edit_search.text.toString().trim())
                true
            } else {
                false
            }
        }
    }

    private fun performSearch(keyword: String) {
        if(keyword.isNotEmpty()) {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(BUNDLE_ON_RESULT_STRING_KEYWORD, keyword)
            })
            finish()
        }
    }

    companion object {
        const val BUNDLE_ON_RESULT_STRING_KEYWORD = "BUNDLE_STRING_SEARCH_KEYWORD"

        fun start(activity: Activity) {
            val intent = Intent(activity, SearchActivity::class.java)
            activity.startActivityForResult(intent,0)
        }
    }
}