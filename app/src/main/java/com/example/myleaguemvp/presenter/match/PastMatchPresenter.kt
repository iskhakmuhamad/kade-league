package com.example.myleaguemvp.presenter.match

import com.example.myleaguemvp.Util.CoroutineContextProvider
import com.example.myleaguemvp.model.match.MatchResponse
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.network.SportDbApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PastMatchPresenter(
    private val view: PastMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPastMatch(idleague: String) {
        view.showLoading()
        GlobalScope.launch(contextProvider.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(SportDbApi.getLastMatch(idleague)).await(),
                MatchResponse::class.java
            )
            view.showMatch(data.events)
            view.hideLoading()
        }
    }
}