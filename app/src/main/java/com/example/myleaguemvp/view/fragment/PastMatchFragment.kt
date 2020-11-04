package com.example.myleaguemvp.view.fragment


import android.content.Intent
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
import com.example.myleaguemvp.Util.KeyUtil
import com.example.myleaguemvp.adapter.MatchAdapter
import com.example.myleaguemvp.model.match.MatchItems
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.presenter.match.PastMatchPresenter
import com.example.myleaguemvp.presenter.match.PastMatchView
import com.example.myleaguemvp.view.activity.MatchDetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_past_match.*

class PastMatchFragment : Fragment(), PastMatchView {

    private lateinit var adapterMatch: MatchAdapter
    private var listMatch: MutableList<MatchItems> = mutableListOf()
    private lateinit var presenter: PastMatchPresenter
    private lateinit var pbPastMatch: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_past_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pbPastMatch = view.findViewById(R.id.pb_l_match)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setSpinnerData()

        adapterMatch = MatchAdapter(listMatch) {
            val intent = Intent(activity?.applicationContext, MatchDetailActivity::class.java)
            intent.putExtra(KeyUtil.KEYMATCH, it.idEvent)
            startActivity(intent)
        }

        rv_past_match.adapter = adapterMatch
        rv_past_match.layoutManager = LinearLayoutManager(activity?.applicationContext)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PastMatchPresenter(this, request, gson)

        spiner_past_match.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (parent?.selectedItem.toString()) {
                    "English Premier League" -> presenter.getPastMatch("4328")
                    "French Ligue 1" -> presenter.getPastMatch("4334")
                    "German Bundesliga" -> presenter.getPastMatch("4331")
                    "Italian Serie A" -> presenter.getPastMatch("4332")
                    "Spanish La Liga" -> presenter.getPastMatch("4335")
                    "American Mayor League" -> presenter.getPastMatch("4346")
                    "Protugeuese Premiera Liga" -> presenter.getPastMatch("4344")
                    "Australian A League" -> presenter.getPastMatch("4356")
                    "Scotish Premier League" -> presenter.getPastMatch("4330")
                    "English League 1" -> presenter.getPastMatch("4396")
                    else -> presenter.getPastMatch("4328")
                }


            }

        }
    }

    fun setSpinnerData() {
        val spinerData = resources.getStringArray(R.array.league_name)
        val spinAdapter = activity?.applicationContext?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item, spinerData)
        }

        spinAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiner_past_match.adapter = spinAdapter
    }

    override fun showLoading() {
        pbPastMatch.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbPastMatch.visibility = View.GONE
    }

    override fun showMatch(data: List<MatchItems>) {
        listMatch.clear()
        listMatch.addAll(data)
        adapterMatch.notifyDataSetChanged()
    }

}
