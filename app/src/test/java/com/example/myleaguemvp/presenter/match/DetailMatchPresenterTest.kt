package com.example.myleaguemvp.presenter.match

import com.example.myleaguemvp.model.match.MatchItems
import com.example.myleaguemvp.model.match.MatchResponse
import com.example.myleaguemvp.model.team.TeamResponse
import com.example.myleaguemvp.model.team.TeamsItem
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

class DetailMatchPresenterTest {

    @Mock
    private lateinit var view: DetailMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResult: Deferred<String>

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, apiRepository, gson, TestContext())
    }

    @Test
    fun getMatchDetail() {
        val matchList = mutableListOf<MatchItems>()
        val response = MatchResponse(matchList, matchList)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResult)
            Mockito.`when`(apiResult.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchDetail("441613")

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchDetail(matchList)
        }
    }

    @Test
    fun getHomeBadge() {
        val homeTeam = mutableListOf<TeamsItem>()
        val team = TeamResponse(homeTeam)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResult)
            Mockito.`when`(apiResult.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(team)
        }
        presenter.getHomeBadge("133602")
        Mockito.verify(view).showHomeBadge(homeTeam)
    }

    @Test
    fun getAwayBadge() {
        val awayteam = mutableListOf<TeamsItem>()
        val team = TeamResponse(awayteam)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResult)
            Mockito.`when`(apiResult.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(team)
        }
        presenter.getAwayBadge("133614")
        Mockito.verify(view).showAwayBadge(awayteam)
        Mockito.verify(view).hideLoading()
    }
}

