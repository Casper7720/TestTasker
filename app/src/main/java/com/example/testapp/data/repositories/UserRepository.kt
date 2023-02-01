package com.example.testapp.data.repositories

import com.example.testapp.data.dao.UserDao
import com.example.testapp.data.webservice.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userApi: Api, private val userDao: UserDao) {

    val data = userDao.findAll()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val users = userApi.getAllAsync().await()
            userDao.add(users)
        }
    }
}