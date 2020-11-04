package com.example.myleaguemvp.presenter.league

import android.annotation.SuppressLint
import android.content.Context
import com.example.myleaguemvp.R
import com.example.myleaguemvp.model.league.LeagueList

class LeaguePresenter(
    private val view: LeagueView,
    private val context: Context
) {
    @SuppressLint("Recycle")
    fun getLeague() {
        view.showLoading()
        val leagueData = mutableListOf<LeagueList>()

        val leagueBadge = context.resources.obtainTypedArray(R.array.league_badge)
        val leagueName = context.resources.getStringArray(R.array.league_name)
        val leagueDes = context.resources.getStringArray(R.array.league_des)
        val leagueId = context.resources.getStringArray(R.array.league_id)

        for (i in leagueId.indices) {
            leagueData.add(
                LeagueList(
                    leagueId[i].toString(),
                    leagueName[i].toString(),
                    leagueDes[i].toString(),
                    leagueBadge.getResourceId(i, 0)
                )
            )
        }
        leagueBadge.recycle()

        view.showLeague(leagueData)
        view.hideLoading()
    }
}