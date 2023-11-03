package com.nikoprayogaw.qrpayment.domain.usecase.users

import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import com.nikoprayogaw.qrpayment.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<User, Flow<Unit>>() {

    override fun execute(params: User): Flow<Unit> {
        return userRepository.addUser(params)
    }
}