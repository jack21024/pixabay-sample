package com.jack.sample.pixabay.search.data.repostitory

import com.jack.baselibrary.repository.BaseRepoData

class SearchRepository(private val searchDataSource: SearchDataSource) {

    private var cache: MutableList<String>? = null

    suspend fun getHistory(): BaseRepoData<List<String>> {
        return BaseRepoData<List<String>>().apply {
            checkAndTakeCache()?.let {
                viewData.postValue(it.asReversed())
            }
        }
    }

    suspend fun saveKeyword(keyword: String) {
        checkAndTakeCache()?.let { cache ->
            if (cache.contains(keyword)) {
                cache.remove(keyword)
            }
            cache.add(keyword)
            saveHistory(cache)
        }
    }

    private suspend fun checkAndTakeCache(): MutableList<String>? {
        takeIf {
            val list = cache ?: loadHistory()
            list != null
        }.let {
            return cache
        }
    }

    private suspend fun loadHistory(): MutableList<String>? {
        cache = searchDataSource.getHistory()
            .takeIf { it.isNotEmpty() }
            ?.split(",")
            ?.toMutableList()
            ?: mutableListOf()
        return cache
    }

    private suspend fun saveHistory(keywordList: List<String>) {
        keywordList.reduce { acc, str ->
            "$acc,$str"
        }.let {
            searchDataSource.saveHistory(it)
        }
    }

}