package com.example.myleaguemvp.presenter.standing

import com.example.myleaguemvp.Util.CoroutineContextProvider
import com.example.myleaguemvp.model.standing.StandingsResponse
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.network.SportDbApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingPresenter(
    private val view: StandingView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getStandings(idleague: String) {
        view.showLoading()
        GlobalScope.launch(contextProvider.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(SportDbApi.getStanding(idleague)).await(),
                StandingsResponse::class.java
            )
            view.showStanding(data.table)
            view.hideLoading()
        }
    }
}