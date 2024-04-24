package com.compose.database.data

import com.compose.database.data.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getAllItemsStream(): Flow<List<Contact>>

    fun getContactStream(id: Int): Flow<Contact?>

    suspend fun insertContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)

    suspend fun updateItem(contact: Contact)
}