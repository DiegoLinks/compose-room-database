package com.compose.database

import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getAllItemsStream(): Flow<List<Contact>>

    fun getItemStream(id: Int): Flow<Contact?>

    suspend fun insertItem(contact: Contact)

    suspend fun deleteItem(contact: Contact)

    suspend fun updateItem(contact: Contact)
}