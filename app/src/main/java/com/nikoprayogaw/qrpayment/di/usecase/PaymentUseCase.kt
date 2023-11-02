package com.nikoprayogaw.qrpayment.di.usecase

import com.nikoprayogaw.qrpayment.domain.repository.payment.PaymentRepository
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepository
import com.nikoprayogaw.qrpayment.domain.usecase.payment.AddPaymentUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.payment.GetPaymentUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.promo.GetPromoUseCase
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PaymentUseCase {
    @Provides
    fun provideGetPaymentUseCase(paymentRepository: PaymentRepository): GetPaymentUseCase {
        return GetPaymentUseCase(paymentRepository)
    }
    @Provides
    fun provideAddPromoUseCase(paymentRepository: PaymentRepository): AddPaymentUseCase {
        return AddPaymentUseCase(paymentRepository)
    }
}