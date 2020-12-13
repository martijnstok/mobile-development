package com.example.madlevel4task2.ui

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.database.Game
import kotlinx.android.synthetic.main.item_score.view.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    //Bind ViewHolder to the data:
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            when (game.result) {
//                0 -> itemView.tvResult.text = R.string.computer_wins.toString()
//                1 -> itemView.tvResult.text = R.string.draw.toString()
//                2 -> itemView.tvResult.text = R.string.player_wins.toString()
                0 -> itemView.tvResult.text = "Computer wins!"
                1 -> itemView.tvResult.text = "Draw"
                2 -> itemView.tvResult.text = "You win!"
            }
            itemView.ivPlayer.setImageResource(Game.DRAWABLES[game.choice])
            itemView.ivComputer.setImageResource(Game.DRAWABLES[game.computerChoice])
        }
    }
}