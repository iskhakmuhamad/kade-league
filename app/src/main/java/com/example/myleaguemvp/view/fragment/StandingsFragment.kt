package com.example.myleaguemvp.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myleaguemvp.R
import com.example.myleaguemvp.adapter.StandingAdapter
import com.example.myleaguemvp.model.standing.StandingItem
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.presenter.standing.StandingPresenter
import com.example.myleaguemvp.presenter.standing.StandingView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_standings.*

class StandingsFragment : Fragment(), StandingView {

    private lateinit var adapter: StandingAdapter
    private var standingList = mutableListOf<StandingItem>()
    private lateinit var presenter: StandingPresenter
    private lateinit var pbStanding: ProgressBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSpinnerData()

        adapter = StandingAdapter(standingList)

        rv_standing.adapter = adapter
        rv_standing.layoutManager = LinearLayoutManager(activity?.applicationContext)

        val request = ApiRepository()
        val gson = Gson()
        presenter = StandingPresenter(this, request, gson)

        spiner_standing.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (parent?.selectedItem.toString()) {
                    "English Premier League" -> presenter.getStandings("4328")
                    "French Ligue 1" -> presenter.getStandings("4334")
                    "German Bundesliga" -> presenter.getStandings("4331")
                    "Italian Serie A" -> presenter.getStandings("4332")
                    "Spanish La Liga" -> presenter.getStandings("4335")
                    "American Mayor League" -> presenter.getStandings("4346")
                    "Protugeuese Premiera Liga" -> presenter.getStandings("4344")
                    "Australian A League" -> presenter.getStandings("4356")
                    "Scotish Premier League" -> presenter.getStandings("4330")
                    "English League 1" -> presenter.getStandings("4396")
                    else -> presenter.getStandings("4328")
                }


            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pbStanding = view.findViewById(R.id.pb_standing)
    }

    fun setSpinnerData() {
        val spinerData = resources.getStringArray(R.array.league_name)
        val spinAdapter = activity?.applicationContext?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item, spinerData)
        }

        spinAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiner_standing.adapter = spinAdapter
    }

    override fun showLoading() {
        pbStanding.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbStanding.visibility = View.GONE
    }

    override fun showStanding(data: List<StandingItem>) {
        standingList.clear()
        standingList.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
