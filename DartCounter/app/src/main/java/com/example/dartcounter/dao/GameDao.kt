package com.example.dartcounter.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dartcounter.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    fun getAllGames(): LiveData<List<Game>>

    @Query("SELECT * FROM gameTable ORDER BY id DESC LIMIT 1")
    fun getLastGame(): LiveData<Game>

    @Update
    suspend fun updateGame(game: Game)

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()
}