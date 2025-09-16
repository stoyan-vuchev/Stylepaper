package com.stoyanvuchev.stylepaper.feature_wallpapers.di

import android.app.Application
import androidx.room.Room
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.WallpapersLocalDatabase
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.remote.WallpapersRemoteAPI
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.repository.WallpapersRepositoryImpl
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.repository.WallpapersRepositorySourceHelper
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.DefaultDispatchers
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.DispatcherProvider
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.repository.WallpapersRepository
import com.stoyanvuchev.stylepaper.feature_wallpapers.framework.manager.DownloadProgressManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WallpapersModule {

    @Provides
    @Singleton
    fun provideDefaultDispatchers(): DispatcherProvider = DefaultDispatchers()

    @Provides
    @Singleton
    fun provideWallpapersRemoteAPI(): WallpapersRemoteAPI {
        return WallpapersRemoteAPI.retrofitInstance
            .create(WallpapersRemoteAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWallpapersLocalDatabase(
        application: Application
    ): WallpapersLocalDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            WallpapersLocalDatabase::class.java,
            "wallpapers_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWallpapersRepositorySourceHelper(
        application: Application,
        api: WallpapersRemoteAPI,
        db: WallpapersLocalDatabase
    ): WallpapersRepositorySourceHelper {
        return WallpapersRepositorySourceHelper(application.applicationContext, api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWallpapersRepository(
        source: WallpapersRepositorySourceHelper
    ): WallpapersRepository {
        return WallpapersRepositoryImpl(source)
    }

    @Provides
    @Singleton
    fun provideDownloadProgressManager(): DownloadProgressManager {
        return DownloadProgressManager()
    }

}