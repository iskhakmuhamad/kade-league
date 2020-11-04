package com.example.myleaguemvp.presenter.match

import com.example.myleaguemvp.model.match.MatchItems

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearch(data: List<MatchItems>)
}