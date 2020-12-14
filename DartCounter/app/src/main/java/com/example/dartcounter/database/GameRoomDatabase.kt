package com.example.dartcounter.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database;
import com.example.dartcounter.dao.GameDao
import com.example.dartcounter.dao.ScoreDao
import com.example.dartcounter.model.Game
import com.example.dartcounter.model.Score
import kotlin.jvm.Volatile;

@Database(entities = arrayOf(Game::class, Score::class), version = 6, exportSchema = false)
abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun GameDao(): GameDao
    abstract fun ScoreDao(): ScoreDao

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameRoomDatabaseInstance: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (gameRoomDatabaseInstance == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if (gameRoomDatabaseInstance == null) {
                        gameRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GameRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return gameRoomDatabaseInstance
        }
    }
}
