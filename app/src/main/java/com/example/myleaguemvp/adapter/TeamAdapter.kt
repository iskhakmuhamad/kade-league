package com.example.myleaguemvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myleaguemvp.R

import com.example.myleaguemvp.model.team.TeamsItem
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(
    private val teams: List<TeamsItem>,
    private val listener: (TeamsItem) -> Unit
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(view)
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position], listener)

    }

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(team: TeamsItem, listener: (TeamsItem) -> Unit) {
            with(itemView) {
                tv_team_name.text = team.strTeam
                tv_team_des.text = team.strDescriptionEN

                Glide.with(this).load(team.strTeamBadge).placeholder(R.drawable.ic_loading_24dp)
                    .into(img_team)

                itemView.setOnClickListener {
                    listener(team)
                }
            }
        }
    }


}