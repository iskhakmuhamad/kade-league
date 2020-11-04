package com.example.myleaguemvp.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.DateTimeUtil
import com.example.myleaguemvp.Util.KeyUtil
import com.example.myleaguemvp.model.league.LeaguesItem
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.presenter.league.LeagueDetailPresenter
import com.example.myleaguemvp.presenter.league.LeagueDetailView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity(),
    LeagueDetailView {

    private lateinit var presenter: LeagueDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        val idLeague = intent.getStringExtra(KeyUtil.KEYLEAGUE)
        val request = ApiRepository()
        val gson = Gson()

        presenter =
            LeagueDetailPresenter(this, request, gson)

        if (idLeague != null) {
            presenter.getLeagueDetail(idLeague)
        }
    }

    override fun showLoading() {
        pb_detail_league.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_detail_league.visibility = View.GONE
    }

    override fun showLeagueDetail(data: List<LeaguesItem>) {
        tv_league_name.text = data[0].strLeague
        tv_estabilished.text = data[0].intFormedYear.toString()
        tv_first_event.text = DateTimeUtil().formatDate(data[0].dateFirstEvent.toString())
        tv_league_description.text = data[0].strDescriptionEN

        Glide.with(this)
            .load(data[0].strBadge)
            .placeholder(R.drawable.ic_loading_24dp)
            .into(img_league_detail)
    }
}
