package com.example.myleaguemvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myleaguemvp.R
import com.example.myleaguemvp.model.standing.StandingItem
import kotlinx.android.synthetic.main.item_standing.view.*

class StandingAdapter(
    private val standingList: List<StandingItem>
) : RecyclerView.Adapter<StandingAdapter.StandingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_standing, parent, false)
        return StandingViewHolder(view)
    }

    override fun getItemCount(): Int = standingList.size

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        holder.bind(standingList[position])
    }

    inner class StandingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: StandingItem) {
            itemView.tvk_win.text = item.win.toString()
            itemView.tvk_goal.text = item.goalsfor.toString()
            itemView.tvk_draw.text = item.draw.toString()
            itemView.tvk_played.text = item.played.toString()
            itemView.tvk_goal_again.text = item.goalsagainst.toString()
            itemView.tvk_score.text = item.total.toString()
            itemView.tvk_team_name.text = item.name
        }
    }
}