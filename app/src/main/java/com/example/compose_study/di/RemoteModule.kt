package com.example.compose_study.di

import com.example.compose_study.data.ApiClient.BASE_URL
import com.example.compose_study.data.PhotoApi
import com.example.compose_study.data.PhotoDataSource
import com.example.compose_study.data.PhotoDataSourceImpl
import com.example.compose_study.data.PhotoRepositoryImpl
import com.example.compose_study.domain.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providePhotoAPiService(retrofit: Retrofit) : PhotoApi =
        retrofit.create(PhotoApi::class.java)

    @Provides
    @Singleton
    fun providePhotoDataSource(
        photoApi: PhotoApi
    ): PhotoDataSource {
        return PhotoDataSourceImpl(photoApi)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(
        dataSource: PhotoDataSource
    ): PhotoRepository {
        return PhotoRepositoryImpl(dataSource)
    }
}
