package com.jack.sample.pixabay.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.jack.baselibrary.viewmodel.BaseViewModel
import com.jack.sample.pixabay.home.ui.recyclerview.item.MediumCardItem
import com.jack.sample.pixabay.home.data.repository.MediumRepository
import com.jack.sample.pixabay.home.enums.MediumOrderType
import kotlinx.coroutines.launch

class HomeViewModel(private val mediumRepository: MediumRepository) : BaseViewModel() {

    val mediumLiveData: MutableLiveData<LiveData<PagedList<MediumCardItem>>> = MutableLiveData()

    override suspend fun loadData() {
        searchMedium()
    }

    fun searchMedium(keyword: String? = null, orderType: MediumOrderType? = null) {
        viewModelScope.launch {
            mediumLiveData.value = mediumRepository.getImageList(keyword, orderType)
        }
    }
}