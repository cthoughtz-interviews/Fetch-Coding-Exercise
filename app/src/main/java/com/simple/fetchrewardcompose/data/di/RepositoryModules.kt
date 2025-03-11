package com.simple.fetchrewardcompose.data.di

import com.simple.fetchrewardcompose.domain.repository.ItemsRepository
import com.simple.fetchrewardcompose.domain.repository.ItemsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {

    @Singleton
    @Binds
    abstract fun provideItemsRepository(itemsRepositoryImpl: ItemsRepositoryImpl): ItemsRepository

}