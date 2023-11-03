package com.nikoprayogaw.qrpayment.domain.usecase.users

import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import com.nikoprayogaw.qrpayment.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UpdateBalanceUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<HashMap<String, Any>, Flow<Unit>>() {

    override fun execute(params: HashMap<String, Any>): Flow<Unit> {
        return userRepository.updateBalance(
            accountNumber = params["accountNumber"] as Long,
            amount = params["amount"] as Long,
        )
    }
}