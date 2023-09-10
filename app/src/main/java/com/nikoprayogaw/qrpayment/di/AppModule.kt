package com.nikoprayogaw.qrpayment.di

import com.nikoprayogaw.qrpayment.presentation.util.AppNavigator
import com.nikoprayogaw.qrpayment.presentation.util.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Singleton
    @Binds
    fun bindAppNavigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator
}