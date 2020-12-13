package com.example.madlevel4task2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.madlevel4task2.R
import com.example.madlevel4task2.database.Game
import com.example.madlevel4task2.database.GameRepo

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var gameRepository: GameRepo
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        gameRepository = GameRepo(this)

        ivRock.setOnClickListener { play(0) }
        ivPaper.setOnClickListener { play(1) }
        ivScissors.setOnClickListener { play(2) }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_history -> {
                val intent = Intent(this, ScoreActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun play(choice: Int) {
        val computersChoice = (0..2).random()
        var result = 1;

        if (choice != computersChoice) {
            when (choice) {
                0 -> {
                    when (computersChoice) {
                        1 -> result = 0
                        2 -> result = 2
                    }
                }
                1 -> {
                    when (computersChoice) {
                        0 -> result = 2
                        2 -> result = 0
                    }
                }
                2 -> {
                    when (computersChoice) {
                        0 -> result = 0
                        1 -> result = 2
                    }
                }
            }
        }

        mainScope.launch {
            val game = Game(
                computerChoice = computersChoice, choice = choice, result = result
            )
            withContext(Dispatchers.IO) {
                gameRepository.addGame(game)
            }
        }

        ivPlayer.setImageResource(Game.DRAWABLES[choice])
        ivComputer.setImageResource(Game.DRAWABLES[computersChoice])

        when (result) {
            0 -> tvResult.text = getString(R.string.computer_wins)
            1 -> tvResult.text = getString(R.string.draw)
            2 -> tvResult.text = getString(R.string.player_wins)
        }

    }
}
