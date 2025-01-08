package com.example.androidplayoground

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*

@Provides: This annotation tells Hilt how to provide the necessary instances for the AppDatabase and NoteDao.

@Singleton scope ensures that the database instance is shared across the application.

@InstallIn(SingletonComponent::class): This annotation ensures that the provided instances are available for the entire app.

 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }
}