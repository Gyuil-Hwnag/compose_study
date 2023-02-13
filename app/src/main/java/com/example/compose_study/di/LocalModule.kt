package com.example.compose_study.di

import android.content.Context
import androidx.room.Room
import com.example.compose_study.data.local.TodoDao
import com.example.compose_study.data.datasource.TodoDataSource
import com.example.compose_study.data.datasource.TodoDataSourceImpl
import com.example.compose_study.data.local.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideTodoDataSource(todoDao: TodoDao): TodoDataSource {
        return TodoDataSourceImpl(todoDao)
    }

    //room
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java, "Compose_Study.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao {
        return todoDatabase.todoDao()
    }
}
