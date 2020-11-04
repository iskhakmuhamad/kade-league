package com.example.myleaguemvp.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.KeyUtil
import com.example.myleaguemvp.adapter.TeamAdapter
import com.example.myleaguemvp.model.team.TeamsItem
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.presenter.team.TeamPresenter
import com.example.myleaguemvp.presenter.team.TeamView
import com.example.myleaguemvp.view.activity.SearchTeamActivity
import com.example.myleaguemvp.view.activity.TeamDetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team.*


class TeamFragment : Fragment(), TeamView {

    private lateinit var adapter: TeamAdapter
    private var teamList: MutableList<TeamsItem> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var pbTeam: ProgressBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSpinnerData()

        adapter = TeamAdapter(teamList) {
            val intent = Intent(activity?.applicationContext, TeamDetailActivity::class.java)
            intent.putExtra(KeyUtil.KEYTEAM, it)
            startActivity(intent)
        }

        rv_team.layoutManager = LinearLayoutManager(activity?.applicationContext)
        rv_team.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamPresenter(this, request, gson)

        spiner_team_league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (parent?.selectedItem.toString()) {
                    "English Premier League" -> presenter.getTeamList("4328")
                    "French Ligue 1" -> presenter.getTeamList("4334")
                    "German Bundesliga" -> presenter.getTeamList("4331")
                    "Italian Serie A" -> presenter.getTeamList("4332")
                    "Spanish La Liga" -> presenter.getTeamList("4335")
                    "American Mayor League" -> presenter.getTeamList("4346")
                    "Protugeuese Premiera Liga" -> presenter.getTeamList("4344")
                    "Australian A League" -> presenter.getTeamList("4356")
                    "Scotish Premier League" -> presenter.getTeamList("4330")
                    "English League 1" -> presenter.getTeamList("4396")
                    else -> presenter.getTeamList("4328")
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pbTeam = view.findViewById(R.id.pb_team_list)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.action_to_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_to_search) {
            startActivity(Intent(activity, SearchTeamActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    fun setSpinnerData() {

        val spinerData = resources.getStringArray(R.array.league_name)
        val spinAdapter = activity?.applicationContext?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item, spinerData)
        }

        spinAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiner_team_league.adapter = spinAdapter
    }

    override fun showLoading() {
        pbTeam.visibility = View.VISIBLE
    }

    override fun showTeamList(teams: List<TeamsItem>) {
        teamList.clear()
        teamList.addAll(teams)
        adapter.notifyDataSetChanged()
    }

    override fun hideLoading() {
        pbTeam.visibility = View.GONE
    }
}
