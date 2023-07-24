package com.example.githubclone

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class GithubCloneApp : Application(){


    override fun onCreate() {
        super.onCreate()

 /*       if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())*/
    }

}