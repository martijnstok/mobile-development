package com.example.dartcounter.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dartcounter.model.Game
import com.example.dartcounter.model.Score

@Dao
interface ScoreDao {
//
//    @Query("SELECT * FROM scoreTable")
//    fun getAllScores(): LiveData<List<Score>>

    @Query("SELECT * FROM scoreTable WHERE player = :player")
    fun getAllScoresByPlayer(player: Int): LiveData<List<Score>>

    @Insert
    suspend fun insertScore(score: Score)

    @Query("DELETE FROM scoreTable")
    suspend fun deleteAllScores()
}