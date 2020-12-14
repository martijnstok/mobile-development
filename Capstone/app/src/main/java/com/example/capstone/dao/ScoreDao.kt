package com.example.capstone.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstone.model.Score

@Dao
interface ScoreDao {

    @Query("SELECT * FROM scoreTable")
    fun getAllScore(): LiveData<List<Score>>

    @Insert
    suspend fun insertScore(score: Score)

    @Query("DELETE FROM scoreTable")
    suspend fun deleteAllScore()
}