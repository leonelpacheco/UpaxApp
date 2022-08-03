package com.leonel.upaxapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.leonel.upaxapp.database.UpaxDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "upax_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UpaxDataBase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db: UpaxDataBase) = db.getEmpleadoDao()
}