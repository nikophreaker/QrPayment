package com.nikoprayogaw.qrpayment.di.usecase

import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepository
import com.nikoprayogaw.qrpayment.domain.usecase.users.AddUserUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.users.GetSpecifiedUsersUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.users.GetUsersUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.users.UpdateBalanceUserUseCase
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UserUseCase {
    @Provides
    fun provideGetUsersUseCase(userRepository: UserRepository): GetUsersUseCase {
        return GetUsersUseCase(userRepository)
    }
    @Provides
    fun provideGetSpecifiedUsersUseCase(userRepository: UserRepository): GetSpecifiedUsersUseCase {
        return GetSpecifiedUsersUseCase(userRepository)
    }
    @Provides
    fun provideAddUserUseCase(userRepository: UserRepository): AddUserUseCase {
        return AddUserUseCase(userRepository)
    }
    @Provides
    fun provideUpdateBalanceUserUseCase(userRepository: UserRepository): UpdateBalanceUserUseCase {
        return UpdateBalanceUserUseCase(userRepository)
    }
}