package com.example.myleaguemvp.presenter.match

import com.example.myleaguemvp.model.match.MatchItems
import com.example.myleaguemvp.model.match.MatchResponse
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

class NextMatchPresenterTest {

    @Mock
    private lateinit var view: NextMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResult: Deferred<String>

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContext())
    }

    @Test
    fun getNextMatch() {
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

            presenter.getNextMatch("4328")

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatch(matchList)
            Mockito.verify(view).hideLoading()
        }
    }
}