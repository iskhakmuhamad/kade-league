package com.example.myleaguemvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.DateTimeUtil
import com.example.myleaguemvp.model.match.MatchItems
import kotlinx.android.synthetic.main.item_match.view.*

class MatchAdapter(
    private val listMatch: List<MatchItems>,
    private val listener: (MatchItems) -> Unit
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int = listMatch.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(listMatch[position], listener)
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MatchItems, listener: (MatchItems) -> Unit) {
            itemView.tv_match_home.text = item.strHomeTeam
            itemView.tv_match_away.text = item.strAwayTeam
            if (!item.dateEvent.isNullOrEmpty()) {
                itemView.tv_match_date.text = DateTimeUtil().formatDate(item.dateEvent.toString())
                itemView.tv_match_time.text = DateTimeUtil().formatTime(item.strTime.toString())
            }
            if (item.intHomeScore != null && item.intAwayScore != null) {
                itemView.tv_score_home.text = item.intHomeScore.toString()
                itemView.tv_score_away.text = item.intAwayScore.toString()
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