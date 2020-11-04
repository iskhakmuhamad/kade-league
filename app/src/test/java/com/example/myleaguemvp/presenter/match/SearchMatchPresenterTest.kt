package com.example.myleaguemvp.presenter.match

import com.example.myleaguemvp.model.match.MatchItems
import com.example.myleaguemvp.model.match.MatchResponse
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.testutil.TestContext
import com.example.myleaguemvp.view.activity.SearchActivity
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchMatchPresenterTest {

    @Mock
    private lateinit var view: SearchActivity

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResult: Deferred<String>

    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, apiRepository, gson, TestContext())
    }

    @Test
    fun getSearch() {
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

            presenter.getSearch("arsenal")

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showSearch(matchList)
            Mockito.verify(view).hideLoading()
        }
    }
}