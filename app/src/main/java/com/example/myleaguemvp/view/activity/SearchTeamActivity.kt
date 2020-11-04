package com.example.myleaguemvp.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.KeyUtil
import com.example.myleaguemvp.adapter.TeamAdapter
import com.example.myleaguemvp.model.team.TeamsItem
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.presenter.team.SearchTeamPresenter
import com.example.myleaguemvp.presenter.team.SearchTeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_team.*

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {

    private lateinit var adapter: TeamAdapter
    private lateinit var presenter: SearchTeamPresenter
    private var searchData = mutableListOf<TeamsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        supportActionBar?.title = getString(R.string.txt_search_team)
        searchData.clear()

        adapter = TeamAdapter(searchData) {
            val intent = Intent(applicationContext, TeamDetailActivity::class.java)
            intent.putExtra(KeyUtil.KEYTEAM, it)
            startActivity(intent)
        }

        rv_search_team.layoutManager = LinearLayoutManager(this.applicationContext)
        rv_search_team.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        presenter = SearchTeamPresenter(this, request, gson)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchAction = menu?.findItem(R.id.action_search_menu)?.actionView as SearchView
        searchAction.queryHint = getString(R.string.txt_search_team)
        searchAction.isFocusable = true

        searchAction.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.getSearchTeam(newText.toString())
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun showLoading() {
        pb_search_team.visibility = View.VISIBLE
    }

    override fun showSearchTeam(data: List<TeamsItem>) {
        val soccerList = mutableListOf<TeamsItem>()
        for (item in data) {
            if (item.strSport.equals("Soccer"))
                soccerList.add(item)
        }
        searchData.clear()
        soccerList.let { searchData.addAll(it) }
        adapter.notifyDataSetChanged()

    }

    override fun hideLoading() {
        pb_search_team.visibility = View.GONE
    }
}
