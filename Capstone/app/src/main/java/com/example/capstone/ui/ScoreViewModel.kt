package com.example.capstone.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.capstone.model.Score
import com.example.capstone.repository.ScoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val scoreRepository = ScoreRepository(application.applicationContext)

    val score: LiveData<List<Score>> = scoreRepository.getAllScore()

    fun insertScore(score: Score) {
        ioScope.launch {
            scoreRepository.insertScore(score)
        }
    }
    fun deleteAllScore() {
        ioScope.launch {
            scoreRepository.deleteAllScore()
        }
    }
}
