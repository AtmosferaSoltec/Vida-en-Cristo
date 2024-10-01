package com.jjmf.vidaencristo.data.module

import com.jjmf.vidaencristo.data.service.DistritoService
import com.jjmf.vidaencristo.data.service.FamiliaService
import com.jjmf.vidaencristo.data.service.MiembroService
import com.jjmf.vidaencristo.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttp(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(interceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideMiembroService(r: Retrofit): MiembroService {
        return r.create(MiembroService::class.java)
    }

    @Singleton
    @Provides
    fun provideDistritoService(r: Retrofit): DistritoService {
        return r.create(DistritoService::class.java)
    }

    @Singleton
    @Provides
    fun provideFamiliaService(r: Retrofit): FamiliaService {
        return r.create(FamiliaService::class.java)
    }

}