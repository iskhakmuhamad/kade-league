package com.example.myleaguemvp.presenter.match

import com.example.myleaguemvp.model.match.MatchItems
import com.example.myleaguemvp.model.team.TeamsItem

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<MatchItems>)
    fun showHomeBadge(homeBadge: List<TeamsItem>)
    fun showAwayBadge(awaBadge: List<TeamsItem>)
}