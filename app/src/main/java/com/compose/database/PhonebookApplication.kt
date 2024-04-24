package com.compose.database

import android.app.Application
import com.compose.database.data.AppContainer
import com.compose.database.data.AppDataContainer

class PhonebookApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}