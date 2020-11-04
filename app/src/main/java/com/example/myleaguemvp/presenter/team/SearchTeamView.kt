package com.example.myleaguemvp.presenter.team

import com.example.myleaguemvp.model.team.TeamsItem

interface SearchTeamView {
    fun showLoading()
    fun showSearchTeam(data: List<TeamsItem>)
    fun hideLoading()
}