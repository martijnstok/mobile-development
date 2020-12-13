package com.example.madlevel4task2.database

import androidx.room.*

@Dao
interface GameDao {

    @Insert
    suspend fun addGame(game: Game)

    @Query("SELECT * FROM scoreTable")
    suspend fun getAllGames(): List<Game>

    @Query("DELETE FROM scoreTable")
    suspend fun emptyGameHistory()
}