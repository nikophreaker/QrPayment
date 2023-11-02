package com.nikoprayogaw.qrpayment.di

import com.nikoprayogaw.qrpayment.data.remote.PromoApi
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepository
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepositoryImpl
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
}