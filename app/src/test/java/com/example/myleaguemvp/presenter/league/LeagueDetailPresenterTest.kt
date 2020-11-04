package com.example.myleaguemvp.presenter.league

import com.example.myleaguemvp.model.league.LeagueResponse
import com.example.myleaguemvp.model.league.LeaguesItem
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.testutil.TestContext
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeagueDetailPresenterTest {

    @Mock
    private lateinit var view: LeagueDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResult: Deferred<String>

    private lateinit var presenter: LeagueDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LeagueDetailPresenter(view, apiRepository, gson, TestContext())
    }

    @Test
    fun getLeagueDetail() {
        val detailLeague = mutableListOf<LeaguesItem>()
        val response = LeagueResponse(detailLeague)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResult)
            Mockito.`when`(apiResult.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLeagueDetail("4328")

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLeagueDetail(detailLeague)
            Mockito.verify(view).hideLoading()
        }
    }
}