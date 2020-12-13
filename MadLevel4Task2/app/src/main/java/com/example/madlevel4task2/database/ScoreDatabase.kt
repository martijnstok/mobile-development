package com.example.madlevel4task2.database

import android.content.Context
import androidx.room.*


//database holder class, main access point to the db data
@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class ScoreDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DB_NAME = "SCORE_DB"

        //Singleton object creation:
        @Volatile
        private var ScoreDatabaseInstance: ScoreDatabase? = null

        fun getDb(context: Context): ScoreDatabase? {
            // if instance or database doesn't exist, created new one
            if (ScoreDatabaseInstance == null) {
                synchronized(ScoreDatabase::class.java) {
                    if (ScoreDatabaseInstance == null) {
                        ScoreDatabaseInstance = Room.databaseBuilder(context.applicationContext,
                            ScoreDatabase::class.java,
                            DB_NAME).build()
                    }
                }
            }
            // Return the existing one:
            return ScoreDatabaseInstance
        }
    }
}