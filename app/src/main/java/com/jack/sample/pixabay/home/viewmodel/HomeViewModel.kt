package com.jack.sample.pixabay.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.jack.baselibrary.viewmodel.BaseViewModel
import com.jack.sample.pixabay.base.recyclerview.item.MediumCardItem
import com.jack.sample.pixabay.home.data.repository.MediumRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(private val mediumRepository: MediumRepository) : BaseViewModel() {

    private var _mediumLiveData: MutableLiveData<LiveData<PagedList<MediumCardItem>>> = MutableLiveData()
    val mediumLiveData = Transformations.map(_mediumLiveData) {
        it
    }


    override suspend fun loadData() {
        // get layout style first
        delay(1000)

        // searching
        searchMedium()
    }

    fun searchMedium(keyword: String? = null) {
        viewModelScope.launch {
            _mediumLiveData.value = mediumRepository.getImageList()
        }
    }
}