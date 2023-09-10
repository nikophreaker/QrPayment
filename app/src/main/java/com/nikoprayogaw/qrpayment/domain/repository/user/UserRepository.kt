package com.nikoprayogaw.qrpayment.domain.repository.user

import androidx.lifecycle.MutableLiveData
import com.nikoprayogaw.qrpayment.domain.model.user.*
import kotlinx.coroutines.*
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    val allUsers = MutableLiveData<List<User>>()
    val foundUser = MutableLiveData<User>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addUser(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.addUser(newUser)
        }
    }

    fun updateUserDetails(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.updateUserDetails(newUser)
        }
    }

    fun getAllUsers() {
        coroutineScope.launch(Dispatchers.IO) {
            allUsers.postValue(userDao.getAllUsers())
        }
    }

    fun deleteUser(employee: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.deleteUser(employee)
        }
    }

    fun findUserByAccountNumber(accountNumber: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            foundUser.postValue(userDao.findUserByAccountNumber(accountNumber))
        }
    }
}