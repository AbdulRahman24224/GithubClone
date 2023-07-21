package com.example.githubclone.di.storage

import android.content.Context
import androidx.room.Room
import com.example.storage.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): AppDatabase {

        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Singleton
    @Provides
    fun provideProductsDao(db: AppDatabase) = db.getReposDao()

}