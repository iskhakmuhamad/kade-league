package com.example.myleaguemvp.presenter.team

import com.example.myleaguemvp.Util.CoroutineContextProvider
import com.example.myleaguemvp.model.team.TeamResponse
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.network.SportDbApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter(
    private val view: SearchTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearchTeam(query: String) {
        view.showLoading()
        GlobalScope.launch(contextProvider.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(SportDbApi.getSearchTeam(query)).await(),
                TeamResponse::class.java
            )
            data?.teams?.let { view.showSearchTeam(it) }
            view.hideLoading()
        }
    }
}