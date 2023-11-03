package com.nikoprayogaw.qrpayment.domain.repository.user

import androidx.lifecycle.MutableLiveData
import com.nikoprayogaw.qrpayment.domain.model.user.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override fun addUser(newUser: User) = flow {
        emit(userDao.addUser(newUser))
    }

    override fun updateUserDetails(newUser: User) = flow {
        emit(userDao.updateUserDetails(newUser))
    }

    override fun updateBalance(accountNumber: Long, amount: Long) = flow {
        emit(userDao.updateBalance(accountNumber, amount))
    }

    override fun getAllUsers() = flow {
        emit(userDao.getAllUsers())
    }

    override fun deleteUser(user: User) = flow {
        emit(userDao.deleteUser(user))
    }

    override fun findUserByAccountNumber(accountNumber: Long) = flow {
        emit(userDao.findUserByAccountNumber(accountNumber))
    }
}