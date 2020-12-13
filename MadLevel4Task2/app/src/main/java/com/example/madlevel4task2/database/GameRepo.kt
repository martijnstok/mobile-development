package com.example.madlevel4task2.database

import android.content.Context

class GameRepo(context: Context) {
    private val gameDao: GameDao

    init {
        val database = ScoreDatabase.getDb(context)
        gameDao = database!!.gameDao()
    }

    suspend fun addGame(game: Game) = gameDao.addGame(game)
    suspend fun getAllGames(): List<Game> = gameDao.getAllGames()
    suspend fun emptyGameHistory() = gameDao.emptyGameHistory()
}