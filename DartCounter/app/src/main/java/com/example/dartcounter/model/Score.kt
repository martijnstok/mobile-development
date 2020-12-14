package com.example.dartcounter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scoreTable")
data class Score (
    @ColumnInfo(name = "score")
    var score: Int,

    @ColumnInfo(name = "player")
    var player: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)