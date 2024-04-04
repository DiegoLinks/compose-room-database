package com.compose.database

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val contactRepository: ContactRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineContactRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ContactRepository]
     */
    override val contactRepository: ContactRepository by lazy {
        OfflineContactRepository(ContactDatabase.getDatabase(context).contactDao())
    }
}