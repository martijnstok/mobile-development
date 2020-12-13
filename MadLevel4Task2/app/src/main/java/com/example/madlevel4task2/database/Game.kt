package com.example.madlevel4task2.database

import android.os.Parcelable
import androidx.room.*
import com.example.madlevel4task2.R
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "scoreTable")
data class Game(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "computerChoice")
    var computerChoice: Int,

    @ColumnInfo(name = "choice")
    var choice: Int,

    @ColumnInfo(name = "result")
    var result: Int

):Parcelable {
    companion object {

        val DRAWABLES = arrayOf(R.drawable.rock, R.drawable.paper, R.drawable.scissors)
    }
}