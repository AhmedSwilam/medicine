package com.task.composetask.repositories

import com.task.composetask.model.data.localdata.User
import com.task.composetask.model.data.localdata.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUser(username: String): User? {
        return userDao.getUser(username)
    }
}