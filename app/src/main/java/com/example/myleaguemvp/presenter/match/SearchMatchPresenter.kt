package com.example.myleaguemvp.presenter.match

import com.example.myleaguemvp.Util.CoroutineContextProvider
import com.example.myleaguemvp.model.match.MatchResponse
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.network.SportDbApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter(
    val view: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextProvider: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearch(e: String) {
        view.showLoading()
        GlobalScope.launch(contextProvider.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(SportDbApi.getSearchMatch(e)).await(),
                MatchResponse::class.java
            )
            data?.event?.let { view.showSearch(it) }
            view.hideLoading()
        }
    }
}