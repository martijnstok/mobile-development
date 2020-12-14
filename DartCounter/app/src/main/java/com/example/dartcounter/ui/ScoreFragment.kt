package com.example.dartcounter.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dartcounter.R
import com.example.dartcounter.model.Score
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_score.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ScoreFragment : Fragment() {
    private val viewModel: ScoreViewModel by viewModels()
    private val gameviewModel: GameViewModel by viewModels()

    private var turn: Int = 1
    val winScore: Int = 501

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        btn_submit.setOnClickListener {
            onAddScore(turn)
        }
    }

    private fun initViews() {
        //Make sure you van access LastGame Table
        gameviewModel.lastGame.observe(viewLifecycleOwner, Observer { game ->
            //Set turn to local value
            turn = game.turn

            //Set active player field
            if (turn == 1) {
                tv_player.text = game.player1name
            } else {
                tv_player.text = game.player2name
            }
        })
    }

    private fun onAddScore(turn: Int) {
        val scoreText: String = etScore.getText().toString()
        val finalValue = scoreText.toInt()

        if (scoreText.isNotBlank() && finalValue >= 0 && finalValue <= 180) {

            //Make sure player doesnt put in higher score than allowed in gane
            if (turn == 1 && calcScorePlayer1(finalValue) <= this.winScore) {
                viewModel.insertScore(Score(finalValue, turn))
                gameviewModel.updateGame(
                    nextTurn(),
                    calcScorePlayer1(finalValue),
                    calcScorePlayer2(0)
                )
            } else if (turn == 2 && calcScorePlayer2(finalValue) <= this.winScore) {
                viewModel.insertScore(Score(finalValue, turn))
                gameviewModel.updateGame(
                    nextTurn(),
                    calcScorePlayer1(0),
                    calcScorePlayer2(finalValue)
                )
            } else {
                Toast.makeText(
                    activity,
                    R.string.to_high_score, Toast.LENGTH_SHORT
                ).show()
                viewModel.insertScore(Score(0, turn))
                gameviewModel.updateGame(nextTurn(), calcScorePlayer1(0), calcScorePlayer2(0))
            }

            findNavController().popBackStack()
        } else {
            Toast.makeText(
                activity,
                R.string.not_valid_score, Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun calcScorePlayer1(score: Int): Int {
        var totalscore: Int = 0

        //Make sure you van access LastGame Table
        gameviewModel.lastGame.observe(viewLifecycleOwner, Observer { game ->
            totalscore = (game.player1score + score)
        })
        return totalscore
    }

    private fun calcScorePlayer2(score: Int): Int {
        var totalscore: Int = 0

        //Make sure you van access LastGame Table
        gameviewModel.lastGame.observe(viewLifecycleOwner, Observer { game ->
            totalscore = (game.player2score + score)
        })
        return totalscore
    }

    //Return next turn
    private fun nextTurn(): Int {
        var nextTurn: Int = 0

        //Make sure you van access LastGame Table
        gameviewModel.lastGame.observe(viewLifecycleOwner, Observer { game ->
            turn = game.turn
        })

        if (turn > 1) {
            nextTurn = 1
        } else {
            nextTurn = 2
        }
        return nextTurn
    }
}