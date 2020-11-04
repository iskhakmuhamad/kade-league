package com.example.myleaguemvp.presenter.league

import com.example.myleaguemvp.Util.CoroutineContextProvider
import com.example.myleaguemvp.model.league.LeagueResponse
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.network.SportDbApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeagueDetailPresenter(
    private val view: LeagueDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLeagueDetail(id: String) {
        view.showLoading()

        GlobalScope.launch(contextProvider.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(SportDbApi.getDetailLeague(id)).await(),
                LeagueResponse::class.java
            )

            view.showLeagueDetail(data.leagues)
            view.hideLoading()
        }
    }

}