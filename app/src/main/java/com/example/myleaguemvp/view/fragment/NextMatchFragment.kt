package com.example.myleaguemvp.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.KeyUtil
import com.example.myleaguemvp.adapter.MatchAdapter
import com.example.myleaguemvp.model.match.MatchItems
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.presenter.match.NextMatchPresenter
import com.example.myleaguemvp.presenter.match.NextMatchView
import com.example.myleaguemvp.view.activity.MatchDetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.*

class NextMatchFragment : Fragment(), NextMatchView {

    private lateinit var adapterMatch: MatchAdapter
    private var listMatch: MutableList<MatchItems> = mutableListOf()
    private lateinit var presenter: NextMatchPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSpinnerData()

        adapterMatch = MatchAdapter(listMatch) {
            val intent = Intent(activity, MatchDetailActivity::class.java)
            intent.putExtra(KeyUtil.KEYMATCH, it.idEvent)
            context?.startActivity(intent)
        }

        rv_next_match.layoutManager = LinearLayoutManager(activity)
        rv_next_match.adapter = adapterMatch

        val request = ApiRepository()
        val gson = Gson()

        presenter = NextMatchPresenter(this, request, gson)

        spiner_next_match.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (parent?.selectedItem.toString()) {
                    "English Premier League" -> presenter.getNextMatch("4328")
                    "French Ligue 1" -> presenter.getNextMatch("4334")
                    "German Bundesliga" -> presenter.getNextMatch("4331")
                    "Italian Serie A" -> presenter.getNextMatch("4332")
                    "Spanish La Liga" -> presenter.getNextMatch("4335")
                    "American Mayor League" -> presenter.getNextMatch("4346")
                    "Protugeuese Premiera Liga" -> presenter.getNextMatch("4344")
                    "Australian A League" -> presenter.getNextMatch("4356")
                    "Scotish Premier League" -> presenter.getNextMatch("4330")
                    "English League 1" -> presenter.getNextMatch("4396")
                    else -> presenter.getNextMatch("4328")
                }


            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    fun setSpinnerData() {
        val spinerData = resources.getStringArray(R.array.league_name)
        val spinAdapter = activity?.applicationContext?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item, spinerData)
        }

        spinAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiner_next_match.adapter = spinAdapter

    }

    override fun showLoading() {
        pb_n_match.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_n_match.visibility = View.GONE
    }

    override fun showMatch(data: List<MatchItems>) {
        listMatch.clear()
        listMatch.addAll(data)
        adapterMatch.notifyDataSetChanged()
    }


}
