package com.example.dartcounter.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dartcounter.dao.GameDao
import com.example.dartcounter.database.GameRoomDatabase
import com.example.dartcounter.model.Game

public class GameRepository(context: Context)  {
    private var gameDao: GameDao
    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase!!.GameDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao.getAllGames() ?: MutableLiveData(emptyList())
    }

    fun getLastGame(): LiveData<Game> {
        return gameDao.getLastGame()
    }

    suspend fun updateGame(game: Game) {
        gameDao.updateGame(game)
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }
}