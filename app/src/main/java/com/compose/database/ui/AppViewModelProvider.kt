package com.compose.database.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.compose.database.PhonebookApplication
import com.compose.database.ui.contact.ContactDetailsViewModel
import com.compose.database.ui.contact.ContactEditViewModel
import com.compose.database.ui.contact.ContactEntryViewModel
import com.compose.database.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ContactEditViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.contactRepository
            )
        }

        initializer {
            ContactEntryViewModel(inventoryApplication().container.contactRepository)
        }

        initializer {
            ContactDetailsViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.contactRepository
            )
        }

        initializer {
            HomeViewModel(inventoryApplication().container.contactRepository)
        }
    }

}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [PhonebookApplication].
 */
fun CreationExtras.inventoryApplication(): PhonebookApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as PhonebookApplication)