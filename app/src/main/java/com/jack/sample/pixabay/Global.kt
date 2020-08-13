package com.jack.sample.pixabay

import androidx.lifecycle.MutableLiveData
import com.jack.sample.pixabay.base.RemoteConfig
import timber.log.Timber

object Session {

    var isAlreadyRemoteConfig = false

    fun init() {
        AppEventHelper.remoteConfigFetchEvent.observeForever {
            Timber.d("fetch remote config $it")
            isAlreadyRemoteConfig = true
        }
    }

}

object AppEventHelper {

    val remoteConfigFetchEvent = MutableLiveData<Boolean>()

    fun init() {
        RemoteConfig.fetch { successful ->
            remoteConfigFetchEvent.postValue(successful)
        }
    }

}