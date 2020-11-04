package com.example.myleaguemvp.view.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.DbTeamUtil
import com.example.myleaguemvp.Util.KeyUtil
import com.example.myleaguemvp.adapter.TeamAdapter
import com.example.myleaguemvp.database.database
import com.example.myleaguemvp.model.team.TeamsItem
import com.example.myleaguemvp.view.activity.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_fav_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavTeamFragment : Fragment() {

    private val datas = arrayListOf<TeamsItem>()
    private lateinit var adapter: TeamAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDataFav()

        adapter = TeamAdapter(datas) {
            val intent = Intent(activity?.applicationContext, TeamDetailActivity::class.java)
            intent.putExtra(KeyUtil.KEYTEAM, it)
            startActivity(intent)
        }
        adapter.notifyDataSetChanged()
        rv_fav_team.layoutManager = LinearLayoutManager(activity)
        rv_fav_team.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav_team, container, false)
    }

    fun getDataFav() {

        datas.clear()
        context?.database?.use {
            val result = select(DbTeamUtil.TABLE_TEAM)
            val favorites = result.parseList(classParser<TeamsItem>())

            datas.addAll(favorites)

            Log.i("DATA DB TEAM", datas.toString())

            if (datas.isEmpty()) {
                tv_nothing_fav.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        getDataFav()
        adapter.notifyDataSetChanged()
        super.onResume()
    }


}
