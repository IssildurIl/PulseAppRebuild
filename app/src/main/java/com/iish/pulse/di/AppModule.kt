package com.iish.pulse.di

import android.content.Context
import androidx.room.Room
import com.iish.pulse.data.database.PulseDatabase
import com.iish.pulse.data.features.user.UserDao
import com.iish.pulse.data.remote.Api
import com.iish.pulse.data.repository.RepositoryImpl
import com.iish.pulse.data.repository.UserRepositoryImpl
import com.iish.pulse.domain.repository.ApiHelper
import com.iish.pulse.domain.repository.UserHelper
import com.iish.pulse.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        val login = HttpLoggingInterceptor()
        login.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient
            .Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(login)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Api::class.java)

    }

    @Provides
    @Singleton
    fun provideApiHelper(api: Api): ApiHelper {
        return RepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserHelper(api: Api): UserHelper {
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): PulseDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            PulseDatabase::class.java,
            "pulse_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(pulseDatabase: PulseDatabase): UserDao = pulseDatabase.userDao()
}