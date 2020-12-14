package com.example.dartcounter.ui

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dartcounter.R
import com.example.dartcounter.model.Score
import kotlinx.android.synthetic.main.fragment_game.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GameFragment : Fragment() {
    private val scorePlayer1 = arrayListOf<Score>()
    private val scorePlayer2 = arrayListOf<Score>()
    private val scoreAdapterPlayer1 = ScoreAdapter(scorePlayer1)
    private val scoreAdapterPlayer2 = ScoreAdapter(scorePlayer2)

    private val viewModel: ScoreViewModel by viewModels()
    private val gameviewModel: GameViewModel by viewModels()

    private val winScore: Int = 501

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddScoreResult()

        btnScore.setOnClickListener {
            findNavController().navigate(R.id.action_gameFragment_to_scoreFragment)
        }
    }

    private fun initViews() {
        rv_score_player1.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_score_player1.adapter = scoreAdapterPlayer1

        rv_score_player2.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_score_player2.adapter = scoreAdapterPlayer2

        //Make sure you van access LastGame Table
        gameviewModel.lastGame.observe(viewLifecycleOwner, Observer { game ->
            tv_player1.text = game.player1name
            tv_player2.text = game.player2name
            tv_score_player1.text = (this.winScore - game.player1score).toString()
            tv_score_player2.text = (this.winScore - game.player2score).toString()

            if(game.turn == 1){
                tv_player1.setTypeface(null, Typeface.BOLD)
                tv_player2.setTypeface(null, Typeface.NORMAL)
            }else{
                tv_player2.setTypeface(null, Typeface.BOLD)
                tv_player1.setTypeface(null, Typeface.NORMAL)
            }

            //Check if player won
            if(game.player1score == this.winScore){
                playerWon(game.player1name)
            }else if (game.player2score  == this.winScore){
                playerWon(game.player2name)
            }
        })
    }

    private fun observeAddScoreResult() {
        //Make sure you van access all score from Player1
        viewModel.scorePlayer1.observe(viewLifecycleOwner, Observer { score ->
            this@GameFragment.scorePlayer1.clear()
            this@GameFragment.scorePlayer1.addAll(score)
            scoreAdapterPlayer1.notifyDataSetChanged()
        })

        //Make sure you van access all score from Player2
        viewModel.scorePlayer2.observe(viewLifecycleOwner, Observer { score ->
            this@GameFragment.scorePlayer2.clear()
            this@GameFragment.scorePlayer2.addAll(score)
            scoreAdapterPlayer2.notifyDataSetChanged()
        })
    }

    //Player won, reset game and give notice to winner
    private fun playerWon(winner: String){
        Toast.makeText(
            activity,
            getString(R.string.winner, winner),
            Toast.LENGTH_LONG
        ).show()
        findNavController().navigate(R.id.action_gameFragment_to_addGameFragment)
    }
}