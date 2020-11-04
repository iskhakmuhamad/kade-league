package com.example.myleaguemvp.presenter.team

import com.example.myleaguemvp.model.team.TeamsItem

interface TeamView {
    fun showLoading()
    fun showTeamList(teams: List<TeamsItem>)
    fun hideLoading()
}