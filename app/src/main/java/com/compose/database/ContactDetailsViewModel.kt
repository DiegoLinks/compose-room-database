package com.compose.database

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ContactDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val contactRepository: ContactRepository,
) : ViewModel() {

    private val contactId: Int =
        checkNotNull(savedStateHandle[ContactDetailsDestination.contactIdArg])

    /**
     * Holds the contact details ui state. The data is retrieved from [ContactRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<ContactDetailsUiState> =
        contactRepository.getContactStream(contactId)
            .filterNotNull()
            .map {
                ContactDetailsUiState(contactDetails = it.toContactDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ContactDetailsUiState()
            )

    /**
     * Deletes the contact from the [ContactRepository]'s data source.
     */
    suspend fun deleteContact() {
        contactRepository.deleteContact(uiState.value.contactDetails.toContact())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for ContactDetailsScreen
 */
data class ContactDetailsUiState(
    val contactDetails: ContactDetails = ContactDetails()
)
