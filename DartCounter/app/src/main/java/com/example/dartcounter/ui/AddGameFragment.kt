package com.example.dartcounter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dartcounter.R
import com.example.dartcounter.model.Game
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_game.*
import kotlinx.android.synthetic.main.fragment_score.*

/**
 * this fragment is here as homescreen and to create the game
 */
class AddGameFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels()
    private val scoreViewModel: ScoreViewModel by viewModels()

    private val startScore: Int = 0
    private val startturn: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Button on click
        btn_create_game.setOnClickListener {
            onAddGame()
        }
    }

    //Add game
    private fun onAddGame() {
        ///Remove all score so it resets
        scoreViewModel.deleteAllScores()

        //Get platernames
        val Player_1_Text: String = et_player_1.getText().toString()
        val Player_2_Text: String = et_player_2.getText().toString()

        //Check if inputs are nor blank
        if (Player_1_Text.isNotBlank() && Player_2_Text.isNotBlank()) {
            //Create game, inserts playernames and startscore
            viewModel.insertGame(Game(Player_1_Text, Player_2_Text, startScore, startScore, startturn))

            //Change to GameFragment
            findNavController().navigate(R.id.action_addGameFragment_to_gameFragment)
        } else {

            //Error message
            Snackbar.make(ivLogo, R.string.not_valid_game, Snackbar.LENGTH_SHORT).show()
        }
    }
}