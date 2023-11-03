package com.nikoprayogaw.qrpayment.domain.usecase.users

import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import com.nikoprayogaw.qrpayment.domain.model.payment.Payment
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.domain.repository.payment.PaymentRepository
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepository
import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import com.nikoprayogaw.qrpayment.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetSpecifiedUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Long, Flow<User>>() {

    override fun execute(params: Long): Flow<User> {
        return userRepository.findUserByAccountNumber(params)
    }
}