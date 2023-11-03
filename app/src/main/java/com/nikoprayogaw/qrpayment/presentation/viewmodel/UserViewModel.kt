package com.nikoprayogaw.qrpayment.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.domain.model.payment.Payment
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import com.nikoprayogaw.qrpayment.domain.usecase.users.AddUserUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.users.GetSpecifiedUsersUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.users.GetUsersUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.users.UpdateBalanceUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getSpecifiedUsersUseCase: GetSpecifiedUsersUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val updateBalanceUserUseCase: UpdateBalanceUserUseCase
) : ViewModel() {

    private val _resourceGetUsers: MutableStateFlow<Resource<List<User>>> = MutableStateFlow(
        Resource.Idle)
    val resourceGetUsers: StateFlow<Resource<List<User>>> = _resourceGetUsers

    private val _resourceGetUser: MutableStateFlow<Resource<User>> = MutableStateFlow(Resource.Idle)
    val resourceGetUser: StateFlow<Resource<User>> = _resourceGetUser

    private val _resourceAddUser: MutableStateFlow<Resource<Unit>> = MutableStateFlow(Resource.Idle)
    val resourceAddUser: StateFlow<Resource<Unit>> = _resourceAddUser

    private val _resourceUpdateBalance: MutableStateFlow<Resource<Unit>> = MutableStateFlow(Resource.Idle)
    val resourceUpdateBalance: StateFlow<Resource<Unit>> = _resourceUpdateBalance

    fun getAllUser(){
        _resourceGetUsers.value = Resource.Loading
        getUsersUseCase.execute(Unit).onEach { result ->
            _resourceGetUsers.value = Resource.Success(result)
        }.catch { e ->
            _resourceGetUsers.value = Resource.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun addUser(user: User) {
        _resourceAddUser.value = Resource.Loading
        addUserUseCase.execute(user).onEach { result ->
            _resourceAddUser.value = Resource.Success(result)
        }.catch { e ->
            _resourceAddUser.value = Resource.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun updateUserDetails(user: User) {
    }

    fun updateBalance(params: HashMap<String, Any>) {
        _resourceUpdateBalance.value = Resource.Loading
        updateBalanceUserUseCase.execute(params).onEach { result ->
            _resourceUpdateBalance.value = Resource.Success(result)
        }.catch { e ->
            _resourceUpdateBalance.value = Resource.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun findUserById(accountNumber: Long) {
        _resourceGetUser.value = Resource.Loading
        getSpecifiedUsersUseCase.execute(accountNumber).onEach { result ->
            _resourceGetUser.value = Resource.Success(result)
        }.catch { e ->
            _resourceGetUser.value = Resource.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun clearedAddUser() {
        viewModelScope.launch {
            _resourceAddUser.value = Resource.Idle
        }
    }

    fun clearedGetUser() {
        viewModelScope.launch {
            _resourceGetUser.value = Resource.Idle
        }
    }

    fun clearedGetUsers() {
        viewModelScope.launch {
            _resourceGetUsers.value = Resource.Idle
        }
    }

    fun clearedUpdateBalance() {
        viewModelScope.launch {
            _resourceUpdateBalance.value = Resource.Idle
        }
    }
}