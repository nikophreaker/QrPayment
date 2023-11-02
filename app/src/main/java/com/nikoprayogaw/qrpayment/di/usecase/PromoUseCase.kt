package com.nikoprayogaw.qrpayment.di.usecase

import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepository
import com.nikoprayogaw.qrpayment.domain.usecase.promo.GetPromoUseCase
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PromoUseCase {
    @Provides
    fun provideGetPromoUseCase(promoRepository: PromoRepository): GetPromoUseCase {
        return GetPromoUseCase(promoRepository)
    }
}