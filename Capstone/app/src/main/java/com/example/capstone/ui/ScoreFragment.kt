package com.example.capstone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.capstone.R
import com.example.capstone.model.Score
import kotlinx.android.synthetic.main.fragment_score.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ScoreFragment : Fragment() {
    private val viewModel: ScoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_submit.setOnClickListener {
            onAddScore()
        }
    }

    private fun onAddScore() {
        val scoreText: String = etScore.getText().toString()
        val finalValue = scoreText.toInt()

        if (scoreText.isNotBlank() && finalValue >= 0 && finalValue <= 180) {
            viewModel.insertScore(Score(finalValue))
            findNavController().popBackStack()

        } else {
            Toast.makeText(
                activity,
                R.string.not_valid_score, Toast.LENGTH_SHORT
            ).show()
        }
    }
}