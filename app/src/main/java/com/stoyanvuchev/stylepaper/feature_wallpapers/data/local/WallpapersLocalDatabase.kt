package com.stoyanvuchev.stylepaper.feature_wallpapers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpaperEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersDiscoverListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersHomeListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.entity.WallpapersSearchListingEntity
import com.stoyanvuchev.stylepaper.feature_wallpapers.data.local.type_converters.WallpapersLocalDatabaseTypeConverters

@Database(
    entities = [
        WallpapersHomeListingEntity::class,
        WallpapersDiscoverListingEntity::class,
        WallpapersSearchListingEntity::class,
        WallpaperEntity::class
    ],
    version = 1
)
@TypeConverters(WallpapersLocalDatabaseTypeConverters::class)
abstract class WallpapersLocalDatabase : RoomDatabase() {
    abstract val dao: WallpapersLocalDatabaseDao
}