package com.example.myleaguemvp.presenter.match

import com.example.myleaguemvp.Util.CoroutineContextProvider
import com.example.myleaguemvp.model.match.MatchResponse
import com.example.myleaguemvp.model.team.TeamResponse
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.network.SportDbApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailMatchPresenter(
    private val view: DetailMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchDetail(id: String) {
        view.showLoading()
        GlobalScope.launch(contextProvider.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(SportDbApi.getMatchDetail(id)).await(),
                MatchResponse::class.java
            )
            view.showMatchDetail(data.events)
        }

    }

    fun getHomeBadge(idHome: String) {
        GlobalScope.launch(contextProvider.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(SportDbApi.getTeamDetail(idHome)).await(),
                TeamResponse::class.java
            )
            view.showHomeBadge(data.teams)
        }
    }

    fun getAwayBadge(idAway: String) {
        GlobalScope.launch(contextProvider.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(SportDbApi.getTeamDetail(idAway)).await(),
                TeamResponse::class.java
            )
            view.showAwayBadge(data.teams)
            view.hideLoading()
        }
    }

}