package com.nikoprayogaw.qrpayment.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val userList: LiveData<List<User>> = userRepository.allUsers

    val foundUser: LiveData<User> = userRepository.foundUser

    fun getAllUser(){
        userRepository.getAllUsers()
    }

    fun addUser(user: User) {
        userRepository.addUser(user)
    }

    fun updateUserDetails(user: User) {
        userRepository.updateUserDetails(user)
    }

    fun findUserById(accountNumber: Long) {
        userRepository.findUserByAccountNumber(accountNumber)
    }

    fun deleteUser(user: User) {
        userRepository.deleteUser(user)
        getAllUser()
    }
}