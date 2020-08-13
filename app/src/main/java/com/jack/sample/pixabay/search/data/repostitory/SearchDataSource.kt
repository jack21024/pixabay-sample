package com.jack.sample.pixabay.search.data.repostitory

import com.jack.sample.pixabay.base.pref.GlobalPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalSearchDataSource(private val pref: GlobalPref) : SearchDataSource {

    override suspend fun getHistory(): String = withContext(Dispatchers.IO) {
        pref.searchHistory
    }

    override suspend fun saveHistory(history: String) = withContext(Dispatchers.IO) {
        pref.searchHistory = history
    }

}

interface SearchDataSource {
    suspend fun getHistory(): String
    suspend fun saveHistory(history: String)
}