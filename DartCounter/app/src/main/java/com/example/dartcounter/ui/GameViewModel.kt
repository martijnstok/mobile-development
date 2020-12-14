package com.example.dartcounter.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dartcounter.model.Game
import com.example.dartcounter.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val lastGame: LiveData<Game> = gameRepository.getLastGame()

    fun insertGame(game: Game) {
        ioScope.launch {
            gameRepository.insertGame(game)
        }
    }

    fun deleteAllGames() {
        ioScope.launch {
            gameRepository.deleteAllGames()
        }
    }

    fun updateGame(turn: Int, player1score: Int, player2score: Int) {

        //if there is an existing note, take that id to update it instead of adding a new one
        val newGame = Game(
            id = lastGame.value?.id,
            player1name = lastGame.value?.player1name.toString(),
            player2name = lastGame.value?.player2name.toString(),
            player1score = player1score,
            player2score = player2score,
            turn = turn
        )

        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.updateGame(newGame)
            }
        }
    }
}