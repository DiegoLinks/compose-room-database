package com.compose.database

import kotlinx.coroutines.flow.Flow

class OfflineContactRepository(private val itemDao: ContactDao) : ContactRepository {
    override fun getAllItemsStream(): Flow<List<Contact>> = itemDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Contact?> = itemDao.getItem(id)

    override suspend fun insertItem(contact: Contact) = itemDao.insert(contact)

    override suspend fun deleteItem(contact: Contact) = itemDao.delete(contact)

    override suspend fun updateItem(contact: Contact) = itemDao.update(contact)
}