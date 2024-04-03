package com.compose.database

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * ViewModel to validate and insert contacts in the Room database.
 */
class ContactEntryViewModel(private val contactRepository: ContactRepository) : ViewModel() {

    /**
     * Holds current contact ui state
     */
    var contactUiState by mutableStateOf(ContactUiState())
        private set

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

    /**
     * Inserts an [Contact] in the Room database
     */
    suspend fun saveContact() {
        if (validateInput()) {
            contactRepository.insertContact(contactUiState.contactDetails.toContact())
        }
    }

    private fun validateInput(uiState: ContactDetails = contactUiState.contactDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && phoneNumber.isNotBlank() && email.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Contact.
 */
data class ContactUiState(
    val contactDetails: ContactDetails = ContactDetails(),
    val isEntryValid: Boolean = false
)

data class ContactDetails(
    val id: Int = 0,
    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
)

/**
 * Extension function to convert [ContactUiState] to [Contact].
 */
fun ContactDetails.toContact(): Contact = Contact(
    id = id,
    name = name,
    phoneNumber = phoneNumber,
    email = email
)

/**
 * Extension function to convert [Contact] to [ContactUiState]
 */
fun Contact.toContactUiState(isEntryValid: Boolean = false): ContactUiState = ContactUiState(
    contactDetails = this.toContactDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Contact] to [ContactDetails]
 */
fun Contact.toContactDetails(): ContactDetails = ContactDetails(
    id = id,
    name = name,
    phoneNumber = phoneNumber,
    email = email
)