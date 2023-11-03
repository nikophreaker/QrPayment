package com.nikoprayogaw.qrpayment.domain.repository.user

import androidx.lifecycle.MutableLiveData
import com.nikoprayogaw.qrpayment.domain.model.user.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {

    fun addUser(newUser: User): Flow<Unit>

    fun updateUserDetails(newUser: User): Flow<Unit>

    fun updateBalance(accountNumber: Long, amount: Long): Flow<Unit>

    fun getAllUsers(): Flow<List<User>>

    fun deleteUser(user: User): Flow<Unit>

    fun findUserByAccountNumber(accountNumber: Long): Flow<User>
}