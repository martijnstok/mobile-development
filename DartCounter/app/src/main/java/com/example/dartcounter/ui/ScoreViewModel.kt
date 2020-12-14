package com.example.dartcounter.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dartcounter.model.Score
import com.example.dartcounter.repository.ScoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val scoreRepository = ScoreRepository(application.applicationContext)

    val scorePlayer1: LiveData<List<Score>> = scoreRepository.getAllScoresByPlayer(1)
    val scorePlayer2: LiveData<List<Score>> = scoreRepository.getAllScoresByPlayer(2)

    fun insertScore(score: Score) {
        ioScope.launch {
            scoreRepository.insertScore(score)
        }
    }
    fun deleteAllScores() {
        ioScope.launch {
            scoreRepository.deleteAllScores()
        }
    }
}