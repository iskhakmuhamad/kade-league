package com.example.myleaguemvp.presenter.match

import com.example.myleaguemvp.model.match.MatchItems

interface PastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<MatchItems>)
}