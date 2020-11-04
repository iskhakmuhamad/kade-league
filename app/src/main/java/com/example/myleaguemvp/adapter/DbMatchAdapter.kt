package com.example.myleaguemvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.DateTimeUtil
import com.example.myleaguemvp.database.DbMatchModel
import kotlinx.android.synthetic.main.item_match.view.*

class DbMatchAdapter(
    private val listMatches: List<DbMatchModel>,
    private val listener: (DbMatchModel) -> Unit
) : RecyclerView.Adapter<DbMatchAdapter.MatchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int = listMatches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(listMatches[position], listener)
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DbMatchModel, listener: (DbMatchModel) -> Unit) {
            itemView.tv_match_home.text = item.homeTeam
            itemView.tv_match_away.text = item.awayTeam
            if (!item.dateEvent.isNullOrEmpty()) {
                itemView.tv_match_date.text = DateTimeUtil().formatDate(item.dateEvent.toString())
                itemView.tv_match_time.text = DateTimeUtil().formatTime(item.timeEvent.toString())
            }
            if (item.homeScore != "null" && item.awayScore != "null") {
                itemView.tv_score_home.text = item.homeScore
                itemView.tv_score_away.text = item.awayScore
            } else {
                itemView.tv_score_home.visibility = View.GONE
                itemView.tv_score_away.visibility = View.GONE
            }
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}