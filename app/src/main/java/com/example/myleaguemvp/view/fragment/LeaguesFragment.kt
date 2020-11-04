package com.example.myleaguemvp.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.KeyUtil
import com.example.myleaguemvp.adapter.LeagueAdapter
import com.example.myleaguemvp.model.league.LeagueList
import com.example.myleaguemvp.presenter.league.LeaguePresenter
import com.example.myleaguemvp.presenter.league.LeagueView
import com.example.myleaguemvp.view.activity.DetailLeagueActivity
import kotlinx.android.synthetic.main.fragment_leagues.*
import org.jetbrains.anko.support.v4.act

class LeaguesFragment : Fragment(), LeagueView {

    private var leagues: MutableList<LeagueList> = mutableListOf()
    private lateinit var presenter: LeaguePresenter
    private lateinit var adapter: LeagueAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = LeagueAdapter(leagues) {
            val intent = Intent(activity, DetailLeagueActivity::class.java)
            intent.putExtra(KeyUtil.KEYLEAGUE, it.id)
            context?.startActivity(intent)
        }

        rv_league.layoutManager = LinearLayoutManager(activity)
        rv_league.adapter = adapter

        presenter = LeaguePresenter(this, act)
        presenter.getLeague()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leagues, container, false)
    }

    override fun showLoading() {
        pb_league.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_league.visibility = View.GONE
    }

    override fun showLeague(data: List<LeagueList>) {
        leagues.clear()
        leagues.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
