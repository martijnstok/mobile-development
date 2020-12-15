package com.example.dartcounter.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dartcounter.dao.ScoreDao
import com.example.dartcounter.database.GameRoomDatabase
import com.example.dartcounter.model.Score

public class ScoreRepository(context: Context) {
    private var scoreDao: ScoreDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        scoreDao = gameRoomDatabase!!.ScoreDao()
    }
//
//    fun getAllScores(): LiveData<List<Score>> {
//        return scoreDao.getAllScores() ?: MutableLiveData(emptyList())
//    }

    fun getAllScoresByPlayer(player: Int): LiveData<List<Score>> {
        return scoreDao.getAllScoresByPlayer(player) ?: MutableLiveData(emptyList())
    }

    suspend fun insertScore(score: Score) {
        scoreDao.insertScore(score)
    }

    suspend fun deleteAllScores() {
        scoreDao.deleteAllScores()
    }
}