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
import com.example.myleaguemvp.adapter.MatchAdapter
import com.example.myleaguemvp.model.match.MatchItems
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.presenter.match.SearchMatchPresenter
import com.example.myleaguemvp.presenter.match.SearchMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchMatchView {

    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: SearchMatchPresenter
    private var searchData: MutableList<MatchItems> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.title = getString(R.string.txt_search_match)
        searchData.clear()
        adapter = MatchAdapter(searchData) {
            val intent = Intent(applicationContext, MatchDetailActivity::class.java)
            intent.putExtra(KeyUtil.KEYMATCH, it.idEvent)
            startActivity(intent)
        }

        rv_search_match.layoutManager = LinearLayoutManager(this.applicationContext)
        rv_search_match.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, request, gson)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchAction = menu?.findItem(R.id.action_search_menu)?.actionView as SearchView
        searchAction.queryHint = getString(R.string.txt_search_match)
        searchAction.isFocusable = true

        searchAction.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.getSearch(newText.toString())
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun showLoading() {
        pb_search_match.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_search_match.visibility = View.GONE
    }

    override fun showSearch(data: List<MatchItems>) {
        val soccerList = mutableListOf<MatchItems>()
        for (item in data) {
            if (item.strSport.equals("Soccer"))
                soccerList.add(item)
        }
        searchData.clear()
        soccerList.let { searchData.addAll(it) }
        adapter.notifyDataSetChanged()
    }
}
