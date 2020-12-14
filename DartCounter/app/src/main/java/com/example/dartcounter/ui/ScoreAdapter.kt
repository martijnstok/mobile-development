package com.example.dartcounter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dartcounter.R
import com.example.dartcounter.model.Score
import kotlinx.android.synthetic.main.item_score.view.*

class ScoreAdapter(private val score: List<Score>): RecyclerView.Adapter<ScoreAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(score: Score) {
          itemView.tvScore.text = score.score.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return score.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(score[position])
    }
}
