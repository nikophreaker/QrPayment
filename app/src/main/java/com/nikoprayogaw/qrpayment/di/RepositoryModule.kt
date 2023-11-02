package com.nikoprayogaw.qrpayment.di

import com.nikoprayogaw.qrpayment.data.remote.PromoApi
import com.nikoprayogaw.qrpayment.domain.model.payment.PaymentDao
import com.nikoprayogaw.qrpayment.domain.model.user.UserDao
import com.nikoprayogaw.qrpayment.domain.repository.payment.PaymentRepository
import com.nikoprayogaw.qrpayment.domain.repository.payment.PaymentRepositoryImpl
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepository
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepositoryImpl
import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePromoRepository(promoApi: PromoApi): PromoRepository {
        return PromoRepositoryImpl(promoApi)
    }

    @Provides
    @Singleton
    fun providePaymentRepository(paymentDao: PaymentDao): PaymentRepository {
        return PaymentRepositoryImpl(paymentDao)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }
}