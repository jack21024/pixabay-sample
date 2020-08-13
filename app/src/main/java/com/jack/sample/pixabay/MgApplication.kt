package com.jack.sample.pixabay

import android.app.Application
import com.jack.sample.pixabay.base.config.RemoteConfig
import com.jack.sample.pixabay.base.pref.GlobalPref
import timber.log.Timber

class MgApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        GlobalPref.init(this)
        RemoteConfig.init()
        AppEventHelper.init()
        Session.init()
    }
}