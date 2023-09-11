package com.nikoprayogaw.qrpayment.di

import android.content.Context
import androidx.room.Room
import com.nikoprayogaw.qrpayment.data.local.AppRoomDatabase
import com.nikoprayogaw.qrpayment.domain.model.payment.PaymentDao
import com.nikoprayogaw.qrpayment.domain.model.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideUserDao(appDatabase: AppRoomDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun providePaymentDao(appDatabase: AppRoomDatabase): PaymentDao {
        return appDatabase.paymentDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppRoomDatabase::class.java,
            "appDB"
        )
            .build()
    }

}