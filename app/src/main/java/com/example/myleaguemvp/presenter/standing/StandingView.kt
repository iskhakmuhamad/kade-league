package com.example.myleaguemvp.presenter.standing

import com.example.myleaguemvp.model.standing.StandingItem

interface StandingView {
    fun showLoading()
    fun hideLoading()
    fun showStanding(data: List<StandingItem>)
}