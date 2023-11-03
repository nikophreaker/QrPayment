package com.nikoprayogaw.qrpayment.di.usecase

import com.nikoprayogaw.qrpayment.domain.repository.porto.PortoRepository
import com.nikoprayogaw.qrpayment.domain.repository.promo.PromoRepository
import com.nikoprayogaw.qrpayment.domain.usecase.porto.GetPortoUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.promo.GetPromoUseCase
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PortoUseCase {
    @Provides
    fun provideGetPortoUseCase(portoRepository: PortoRepository): GetPortoUseCase {
        return GetPortoUseCase(portoRepository)
    }
}