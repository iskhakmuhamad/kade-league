package com.example.myleaguemvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myleaguemvp.R
import com.example.myleaguemvp.model.league.LeagueList
import kotlinx.android.synthetic.main.item_league.view.*

class LeagueAdapter(
    private val listLeagues: List<LeagueList>,
    private val listener: (LeagueList) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        return LeagueViewHolder(view)
    }

    override fun getItemCount(): Int = listLeagues.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(listLeagues[position], listener)

    }

    inner class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(leaguesItem: LeagueList, listener: (LeagueList) -> Unit) {
            with(itemView) {
                tv_league_name.text = leaguesItem.name
                tv_league_des.text = leaguesItem.des

                Glide.with(this).load(leaguesItem.badge).placeholder(R.drawable.ic_loading_24dp)
                    .into(img_league)

                itemView.setOnClickListener {
                    listener(leaguesItem)
                }
            }
        }
    }


}