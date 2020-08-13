package com.jack.sample.pixabay

import androidx.lifecycle.MutableLiveData
import com.jack.baselibrary.common.BaseInstanceObject
import com.jack.sample.pixabay.base.config.RemoteConfig
import timber.log.Timber

object Session : BaseInstanceObject() {

    var isAlreadyRemoteConfig = false

    override fun onInit() {
        AppEventHelper.remoteConfigFetchEvent.observeForever {
            Timber.d("fetch remote config $it")
            isAlreadyRemoteConfig = true
        }
    }

}

object AppEventHelper : BaseInstanceObject() {

    val remoteConfigFetchEvent = MutableLiveData<Boolean>()

    override fun onInit() {
        RemoteConfig.fetch { successful ->
            remoteConfigFetchEvent.postValue(successful)
        }
    }

}