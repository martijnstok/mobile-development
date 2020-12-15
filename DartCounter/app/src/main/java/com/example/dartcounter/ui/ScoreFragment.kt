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
import com.google.android.material.snackbar.Snackbar
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
    val maxthrow: Int = 180

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

        //set button onClickListener
        btn_submit.setOnClickListener {
            //Voeg score toe
            onAddScore(turn)
        }
    }

    //Create function to load the view
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

    //Toevoegen van score
    private fun onAddScore(turn: Int) {
        //Get input user
        val scoreText: String = etScore.getText().toString()
        val finalValue = scoreText.toInt()

        //check if inoput is valid
        if (scoreText.isNotBlank() && finalValue >= 0 && finalValue <= this.maxthrow) {

            //Make sure player doesnt put in higher score than allowed in gane
            if (turn == 1 && calcScorePlayer1(finalValue) <= this.winScore) {
                //Inset score for player 1

                //insert score
                viewModel.insertScore(Score(finalValue, turn))

                //Update game
                gameviewModel.updateGame(
                    nextTurn(),
                    calcScorePlayer1(finalValue),
                    calcScorePlayer2(0)
                )
            } else if (turn == 2 && calcScorePlayer2(finalValue) <= this.winScore) {
                //Inset score for player 2

                //insert score
                viewModel.insertScore(Score(finalValue, turn))

                //Update game
                gameviewModel.updateGame(
                    nextTurn(),
                    calcScorePlayer1(0),
                    calcScorePlayer2(finalValue)
                )
            } else {
                //Give user feedback that score is to hight
                Snackbar.make(tv_player, R.string.to_high_score, Snackbar.LENGTH_SHORT).show()

                //Insert score
                viewModel.insertScore(Score(0, turn))

                //Update gamem with both added 0
                gameviewModel.updateGame(nextTurn(), calcScorePlayer1(0), calcScorePlayer2(0))
            }

            //Change fragment back, to GameFragment
            findNavController().popBackStack()
        } else {
            //Give user feedback that score is not valid
            Snackbar.make(tv_player, R.string.not_valid_score, Snackbar.LENGTH_SHORT).show()
        }
    }

    //Calculate totalscore for player 1 by thow
    private fun calcScorePlayer1(score: Int): Int {
        var totalscore: Int = 0

        //Make sure you van access LastGame Table
        gameviewModel.lastGame.observe(viewLifecycleOwner, Observer { game ->
            totalscore = (game.player1score + score)
        })
        return totalscore
    }

    //Calculate totalscore for player 2 by thow
    private fun calcScorePlayer2(score: Int): Int {
        var totalscore: Int = 0

        //Make sure you van access LastGame Table
        gameviewModel.lastGame.observe(viewLifecycleOwner, Observer { game ->
            totalscore = (game.player2score + score)
        })
        return totalscore
    }

    //Return the next turn
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