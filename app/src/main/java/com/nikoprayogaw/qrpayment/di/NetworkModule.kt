package com.nikoprayogaw.qrpayment.di

import android.content.Context
import com.nikoprayogaw.qrpayment.common.AuthorizationInterceptor
import com.nikoprayogaw.qrpayment.common.network.NetworkConfig
import com.nikoprayogaw.qrpayment.data.remote.PortoApi
import com.nikoprayogaw.qrpayment.data.remote.PromoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverter: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConfig.getBaseUrl())
            .addConverterFactory(gsonConverter)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideClient(authorizationInterceptor: AuthorizationInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideAuthorizationInterceptor(@ApplicationContext appContext: Context): AuthorizationInterceptor = AuthorizationInterceptor(appContext)

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory =
        GsonConverterFactory.create(
            NetworkConfig.gson()
        )

    @Provides
    @Singleton
    fun providePromoApi(retorfit: Retrofit): PromoApi {
        return retorfit.create(PromoApi::class.java)
    }
    @Provides
    @Singleton
    fun providePortoApi(retorfit: Retrofit): PortoApi {
        return retorfit.create(PortoApi::class.java)
    }
}