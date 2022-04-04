package com.apps.newsapp

import android.app.Application
import com.apps.newsapp.di.appModule
import com.apps.newsapp.di.repoModule
import com.apps.newsapp.di.viewmodelModule
import org.koin.android.ext.android.startKoin

class NewApp : Application() {

    override fun onCreate() {
        super.onCreate()
       startKoin(this,modules = listOf(appModule, viewmodelModule, repoModule))
    }

}