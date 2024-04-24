package com.compose.database.data

import com.compose.database.data.Contact
import com.compose.database.data.ContactDao
import com.compose.database.data.ContactRepository
import kotlinx.coroutines.flow.Flow

class OfflineContactRepository(private val itemDao: ContactDao) : ContactRepository {
    override fun getAllItemsStream(): Flow<List<Contact>> = itemDao.getAllItems()

    override fun getContactStream(id: Int): Flow<Contact?> = itemDao.getItem(id)

    override suspend fun insertContact(contact: Contact) = itemDao.insert(contact)

    override suspend fun deleteContact(contact: Contact) = itemDao.delete(contact)

    override suspend fun updateItem(contact: Contact) = itemDao.update(contact)
}