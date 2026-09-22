package br.com.moneynews.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class MoneyNewsDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: MoneyNewsDatabase? = null

        fun getInstance(context: Context): MoneyNewsDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MoneyNewsDatabase::class.java,
                    "money_news.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}