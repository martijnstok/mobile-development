package com.example.capstone.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone.dao.ScoreDao
import com.example.capstone.database.ScoreRoomDatabase
import com.example.capstone.model.Score

public class ScoreRepository(context: Context)  {
    private var scoreDao: ScoreDao
    init {
        val scoreRoomDatabase = ScoreRoomDatabase.getDatabase(context)
        scoreDao = scoreRoomDatabase!!.scoreDao()
    }

    fun getAllScore(): LiveData<List<Score>> {
        return scoreDao.getAllScore() ?: MutableLiveData(emptyList())
    }

    suspend fun insertScore(score: Score) {
        scoreDao.insertScore(score)
    }

    suspend fun deleteAllScore() {
        scoreDao.deleteAllScore()
    }
}