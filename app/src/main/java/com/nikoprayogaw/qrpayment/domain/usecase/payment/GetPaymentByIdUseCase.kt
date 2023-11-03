package com.nikoprayogaw.qrpayment.domain.usecase.payment

import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoResponse
import com.nikoprayogaw.qrpayment.domain.model.payment.Payment
import com.nikoprayogaw.qrpayment.domain.repository.payment.PaymentRepository
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepository
import com.nikoprayogaw.qrpayment.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetPaymentByIdUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) : BaseUseCase<Int, Flow<Payment>>() {

    override fun execute(params: Int): Flow<Payment> {
        return paymentRepository.findPaymentById(params)
    }
}