package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.example.madlevel4task2.R
import com.example.madlevel4task2.database.Game
import com.example.madlevel4task2.database.GameRepo
import kotlinx.android.synthetic.main.activity_game_score.*
import kotlinx.android.synthetic.main.content_game_score.*
import kotlinx.coroutines.*

class ScoreActivity : AppCompatActivity() {

    private val gameArray = arrayListOf<Game>()
    private val gameAdapter =
        GameAdapter(gameArray)
    private lateinit var gameRepo: GameRepo
    private val mainScope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_score)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        gameRepo = GameRepo(this)

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                clearGameHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        rvHistory.layoutManager =
            LinearLayoutManager(this@ScoreActivity, RecyclerView.VERTICAL, false)
        rvHistory.adapter = gameAdapter
        rvHistory.addItemDecoration(
            DividerItemDecoration(
                this@ScoreActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        getGameHistory()
    }

    private fun getGameHistory() {
        mainScope.launch {
            val gameList = withContext(Dispatchers.IO) {
                gameRepo.getAllGames()
            }
            this@ScoreActivity.gameArray.clear()
            this@ScoreActivity.gameArray.addAll(gameList)
            this@ScoreActivity.gameArray.reverse()
            this@ScoreActivity.gameAdapter.notifyDataSetChanged()
        }
    }

    private fun clearGameHistory() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepo.emptyGameHistory()
            }
            getGameHistory()
            Toast.makeText(this@ScoreActivity, R.string.cleared_history, Toast.LENGTH_SHORT).show()
        }
    }

}
