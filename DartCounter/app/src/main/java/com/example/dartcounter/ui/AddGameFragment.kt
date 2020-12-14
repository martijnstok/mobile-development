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
import kotlinx.android.synthetic.main.fragment_add_game.*
import kotlinx.android.synthetic.main.fragment_score.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddGameFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels()
    private val scoreViewModel: ScoreViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_create_game.setOnClickListener {
            onAddGame()
        }
    }

    private fun onAddGame() {
        scoreViewModel.deleteAllScores()
        val Player_1_Text: String = et_player_1.getText().toString()
        val Player_2_Text: String = et_player_2.getText().toString()

        if (Player_1_Text.isNotBlank() && Player_2_Text.isNotBlank()) {
            viewModel.insertGame(Game(Player_1_Text, Player_2_Text, 0,0,1))
            findNavController().navigate(R.id.action_addGameFragment_to_gameFragment)
        } else {
            Toast.makeText(
                activity,
                R.string.not_valid_game, Toast.LENGTH_SHORT
            ).show()
        }
    }
}