package com.nikoprayogaw.qrpayment.presentation.user_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import com.nikoprayogaw.qrpayment.presentation.util.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val userRepository: UserRepository
) : ViewModel() {

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }

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

    fun findUserById(accountNumber: String) {
        userRepository.findUserByAccountNumber(accountNumber)
    }

    fun deleteUser(user: User) {
        userRepository.deleteUser(user)
        getAllUser()
    }
}