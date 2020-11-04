package com.example.myleaguemvp.view.activity

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.DateTimeUtil
import com.example.myleaguemvp.Util.DbMatchUtil
import com.example.myleaguemvp.Util.KeyUtil
import com.example.myleaguemvp.database.DbMatchModel
import com.example.myleaguemvp.database.database
import com.example.myleaguemvp.model.match.MatchItems
import com.example.myleaguemvp.model.team.TeamsItem
import com.example.myleaguemvp.network.ApiRepository
import com.example.myleaguemvp.presenter.match.DetailMatchPresenter
import com.example.myleaguemvp.presenter.match.DetailMatchView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchDetailActivity : AppCompatActivity(), DetailMatchView {

    private var matchItem: MutableList<MatchItems> = mutableListOf()
    private lateinit var presenter: DetailMatchPresenter
    private lateinit var btnFav: FloatingActionButton
    private lateinit var dbMatchData: DbMatchModel
    private var isFav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val idEvent = intent.getStringExtra(KeyUtil.KEYMATCH)
        btnFav = findViewById(R.id.btn_add_fav)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)

        if (idEvent != null) {
            presenter.getMatchDetail(idEvent)
            favState(idEvent)
        }
        setFavorite()
        btnFav.setOnClickListener {
            if (isFav) {
                removeFavorite(dbMatchData.idEvent.toString())
                isFav = !isFav
                setFavorite()
            } else {
                addFavorite(dbMatchData)
                isFav = !isFav
                setFavorite()
            }
        }


    }

    override fun showLoading() {
        pb_detail_event.visibility = View.VISIBLE
    }

    @SuppressLint("RestrictedApi")
    override fun hideLoading() {
        pb_detail_event.visibility = View.GONE
        btn_add_fav.visibility = View.VISIBLE
    }

    override fun showMatchDetail(data: List<MatchItems>) {
        var tipe = "next"
        matchItem.clear()
        matchItem.addAll(data)
        tvd_event.text = matchItem[0].strEvent
        tvd_league_event.text = matchItem[0].strLeague
        tvd_date_event.text = DateTimeUtil().formatDate(data[0].dateEvent.toString())
        tvd_time_event.text = DateTimeUtil().formatTime(data[0].strTime.toString())
        tvd_season.text = data[0].strSeason
        if (data[0].intHomeScore != null) {
            tipe = "past"
            tvd_score_home.text = data[0].intHomeScore.toString()
            tvd_score_away.text = data[0].intAwayScore.toString()
        } else {
            tvd_score_home.visibility = View.GONE
            tvd_score_away.visibility = View.GONE
            txt_min.text = getString(R.string.txt_yet_match)
        }

        dbMatchData = DbMatchModel(
            matchItem[0].idEvent,
            matchItem[0].strLeague,
            matchItem[0].strEvent,
            matchItem[0].strHomeTeam,
            matchItem[0].strAwayTeam,
            matchItem[0].dateEvent,
            matchItem[0].strTime,
            matchItem[0].intHomeScore.toString(),
            matchItem[0].intAwayScore.toString(),
            matchItem[0].idHomeTeam.toString(),
            matchItem[0].idAwayTeam.toString(),
            matchItem[0].strSeason,
            tipe
        )

        presenter.getHomeBadge(matchItem[0].idHomeTeam.toString())
        presenter.getAwayBadge(matchItem[0].idAwayTeam.toString())
    }

    override fun showHomeBadge(homeBadge: List<TeamsItem>) {
        dbMatchData.homeBadge = homeBadge[0].strTeamBadge
        Glide.with(this).load(homeBadge[0].strTeamBadge).placeholder(R.drawable.ic_loading_24dp)
            .into(img_home)
    }

    override fun showAwayBadge(awaBadge: List<TeamsItem>) {
        dbMatchData.awayBadge = awaBadge[0].strTeamBadge
        Glide.with(this).load(awaBadge[0].strTeamBadge).placeholder(R.drawable.ic_loading_24dp)
            .into(img_away)
    }

    fun addFavorite(data: DbMatchModel) {
        try {
            database.use {
                insert(
                    DbMatchUtil.TABLE_FAVORITE,
                    DbMatchUtil.ID to data.idEvent,
                    DbMatchUtil.LEAGUE_NAME to data.leagueName,
                    DbMatchUtil.EVENT_MATCH to data.eventMatch,
                    DbMatchUtil.HOME_TEAM to data.homeTeam,
                    DbMatchUtil.AWAY_TEAM to data.awayTeam,
                    DbMatchUtil.DATE_EVENT to data.dateEvent,
                    DbMatchUtil.TIME_EVENT to data.timeEvent,
                    DbMatchUtil.HOME_SCORE to data.homeScore,
                    DbMatchUtil.AWAY_SCORE to data.awayScore,
                    DbMatchUtil.HOME_BADGE to data.homeBadge,
                    DbMatchUtil.AWAY_BADGE to data.awayBadge,
                    DbMatchUtil.SEASON to data.season,
                    DbMatchUtil.TIPE to data.tipe
                )
            }
            Toast.makeText(
                applicationContext,
                "BERHASIL MANAMBAHKAN DATA KE FAVORITE",
                Toast.LENGTH_SHORT
            )
                .show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(applicationContext, "GAGAL TAMBAH KE FAVORITE", Toast.LENGTH_SHORT)
                .show()
            Log.i("GAGAL TAMBAHKE DATABASE", e.toString())
        }
    }

    private fun removeFavorite(idEvent: String) {
        try {
            database.use {
                delete(DbMatchUtil.TABLE_FAVORITE, "(id = {id})", "id" to idEvent)
            }
            Toast.makeText(
                applicationContext,
                "BERHASIL MENGHAPUS DARI FAVORITE",
                Toast.LENGTH_SHORT
            )
                .show()
        } catch (e: SQLiteConstraintException) {
            Log.i("GAGAL HAPUS ", e.toString())
        }
    }

    private fun setFavorite() {
        if (isFav) {
            btnFav.setImageResource(R.drawable.ic_favorite)
        } else {
            btnFav.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun favState(idfav: String) {
        database.use {
            val get = select(DbMatchUtil.TABLE_FAVORITE).whereArgs("(id = {id})", "id" to idfav)
            val favorites = get.parseList(classParser<DbMatchModel>())
            if (favorites.isNotEmpty()) {
                isFav = true
            }
        }
    }
}
