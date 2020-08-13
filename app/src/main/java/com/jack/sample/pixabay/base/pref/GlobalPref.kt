package com.jack.sample.pixabay.base.pref

import android.content.Context
import android.content.SharedPreferences
import com.jack.baselibrary.preferences.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GlobalPref : Preferences() {

    private const val PREF_NAME = "sample_pixabay"

    override lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) = this.apply {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    var searchHistory: String
        get() = getString("search_history")
        set(value) = putString("search_history", value)

}

