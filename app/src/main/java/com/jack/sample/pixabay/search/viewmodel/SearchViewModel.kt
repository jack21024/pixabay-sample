package com.jack.sample.pixabay.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.jack.baselibrary.repository.BaseRepoData
import com.jack.baselibrary.viewmodel.BaseViewModel
import com.jack.sample.pixabay.search.data.repostitory.SearchRepository
import kotlinx.coroutines.launch


class SearchViewModel(private val searchRepository: SearchRepository) : BaseViewModel() {

    private val _historyListData = MutableLiveData<BaseRepoData<List<String>>>()
    val historyListData = Transformations.map(_historyListData) {
        it.viewData
    }

    override suspend fun loadData() {
        getKeywordHistory()
    }

    private suspend fun getKeywordHistory() {
        searchRepository.getHistory().let {
            _historyListData.postValue(it)
        }
    }

    fun saveKeyword(keyword: String) {
        viewModelScope.launch {
            searchRepository.saveKeyword(keyword)
            getKeywordHistory()
        }
    }

}