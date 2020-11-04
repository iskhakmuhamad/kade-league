package com.example.myleaguemvp.presenter.league

import com.example.myleaguemvp.model.league.LeaguesItem

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetail(data: List<LeaguesItem>)
}
