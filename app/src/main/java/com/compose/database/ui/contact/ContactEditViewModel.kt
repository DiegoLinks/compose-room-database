package com.compose.database.ui.contact

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.compose.database.data.ContactRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ContactEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val contactRepository: ContactRepository
) : ViewModel() {

    /**
     * Holds current contact ui state
     */
    var contactUiState by mutableStateOf(ContactUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ContactEditDestination.contactIdArg])

    init {
        viewModelScope.launch {
            contactUiState = contactRepository.getContactStream(itemId)
                .filterNotNull()
                .first()
                .toContactUiState(true)
        }
    }

    /**
     * Update the contact in the [ContactRepository]'s data source
     */
    suspend fun updateContact() {
        if (validateInput(contactUiState.contactDetails)) {
            contactRepository.updateItem(contactUiState.contactDetails.toContact())
        }
    }

    /**
     * Updates the [contactUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(contactDetails: ContactDetails) {
        contactUiState =
            ContactUiState(
                contactDetails = contactDetails,
                isEntryValid = validateInput(contactDetails)
            )
    }

    private fun validateInput(uiState: ContactDetails = contactUiState.contactDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && phoneNumber.isNotBlank() && email.isNotBlank()
        }
    }
}