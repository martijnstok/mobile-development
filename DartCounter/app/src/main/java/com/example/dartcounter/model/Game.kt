package com.example.dartcounter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameTable")
data class Game(
    @ColumnInfo(name = "player1name")
    var player1name: String,

    @ColumnInfo(name = "player2name")
    var player2name: String,

    @ColumnInfo(name = "player1score")
    var player1score: Int,

    @ColumnInfo(name = "player2score")
    var player2score: Int,

    @ColumnInfo(name = "turn")
    var turn: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)