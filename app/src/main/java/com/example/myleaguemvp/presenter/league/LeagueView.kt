package com.example.myleaguemvp.presenter.league

import com.example.myleaguemvp.model.league.LeagueList

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showLeague(data: List<LeagueList>)
}