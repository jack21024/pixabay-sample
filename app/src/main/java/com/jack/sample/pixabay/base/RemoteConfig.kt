package com.jack.sample.pixabay.base

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.jack.sample.pixabay.BuildConfig
import com.jack.sample.pixabay.R
import com.jack.sample.pixabay.home.enums.MediumLayoutStyle
import java.util.concurrent.TimeUnit

object RemoteConfig {
    private val TAG = RemoteConfig::class.java.simpleName

    private val fbRemoteConfig: FirebaseRemoteConfig
        get() = FirebaseRemoteConfig.getInstance()

    private val cacheExpiration = if (BuildConfig.DEBUG) 0 else {
        TimeUnit.HOURS.toSeconds(12)
    }

    val mediumLayoutStyle: MediumLayoutStyle
        get() {
            val isGridEnable = fbRemoteConfig.getBoolean("bool_enable_medium_style_grid")
            return if(isGridEnable) {
                MediumLayoutStyle.GRID
            } else {
                MediumLayoutStyle.LIST
            }
        }

    fun init() {
        fbRemoteConfig.apply {
            val fbRemoteConfigSetting =
                FirebaseRemoteConfigSettings.Builder().apply {
                    setDeveloperModeEnabled(BuildConfig.DEBUG)
                }.build()
            setConfigSettings(fbRemoteConfigSetting)
            setDefaults(R.xml.remote_config_default)
            activateFetched()
        }
    }

    fun fetch(onCompleted: ((successful: Boolean) -> Unit)?) {
        Log.d(TAG, "fetch firebase remote config...")
        fbRemoteConfig.apply {
            fetch(cacheExpiration).addOnCompleteListener {
                Log.d(TAG, "firebase fetch completed.")
                if (it.isSuccessful) {
                    this.activateFetched()
                }
                onCompleted?.invoke(it.isSuccessful)
            }
        }
    }

}