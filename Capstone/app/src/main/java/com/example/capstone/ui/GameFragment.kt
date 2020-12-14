package com.example.capstone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.model.Score
import kotlinx.android.synthetic.main.fragment_game.*


class GameFragment : Fragment() {

    private val score = arrayListOf<Score>()
    private val scoreAdapter = ScoreAdapter(score)

    private val viewModel: ScoreViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddScoreResult()

        view.findViewById<Button>(R.id.btnScore).setOnClickListener {
            findNavController().navigate(R.id.action_gameFragment_to_scoreFragment)
        }
    }

    private fun initViews() {
        rv_score_player1.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_score_player1.adapter = scoreAdapter
//        rv_score_player1.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
    }

    private fun observeAddScoreResult() {
        viewModel.score.observe(viewLifecycleOwner, Observer { score ->
            this@GameFragment.score.clear()
            this@GameFragment.score.addAll(score)
            scoreAdapter.notifyDataSetChanged()
        })
    }
}