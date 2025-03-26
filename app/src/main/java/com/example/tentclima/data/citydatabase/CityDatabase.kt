package com.example.tentclima.data.citydatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [CityEntity::class], version = 1)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCityDatabase(@ApplicationContext context: Context): CityDatabase {
        return Room.databaseBuilder(
            context,
            CityDatabase::class.java,
            "Cities" // Mantenha o nome do banco de dados
        ).build()
    }

    @Singleton
    @Provides
    fun provideCityDao(db: CityDatabase): CityDao {
        return db.cityDao()
    }
}


//object CityDatabaseProvider {
//
//    @Volatile
//    private var INSTANCE: CityDatabase? = null
//
//    fun provide(context: Context): CityDatabase {
//        return INSTANCE ?: synchronized(this) {
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                CityDatabase::class.java,
//                "Cities"
//            ).build()
//            INSTANCE = instance
//            instance
//        }
//    }
//}