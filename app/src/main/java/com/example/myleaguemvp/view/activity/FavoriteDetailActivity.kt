@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_favorite_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FavoriteDetailActivity : AppCompatActivity() {

    private lateinit var btnFav: FloatingActionButton
    private var isFav: Boolean = false

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_detail)

        val matchData: DbMatchModel = intent.getParcelableExtra(KeyUtil.KEYFAVORITE)

        if (matchData.idEvent != null) {
            favState(matchData.idEvent.toString())
        }
        tvd_league_event.text = matchData.leagueName
        tvd_date_event.text = DateTimeUtil().formatDate(matchData.dateEvent.toString())
        tvd_event.text = matchData.eventMatch
        tvd_time_event.text = DateTimeUtil().formatTime(matchData.timeEvent.toString())
        if (matchData.homeScore != "null" && matchData.awayScore != "null") {

            tvd_score_home.text = matchData.homeScore
            tvd_score_away.text = matchData.awayScore

        } else {
            tvd_score_away.visibility = View.GONE
            tvd_score_home.visibility = View.GONE
            txt_min.text = getString(R.string.txt_yet_match)
        }
        tvd_season.text = matchData.season
        Glide.with(this).load(matchData.homeBadge).placeholder(R.drawable.ic_loading_24dp)
            .into(img_home)
        Glide.with(this).load(matchData.awayBadge).placeholder(R.drawable.ic_loading_24dp)
            .into(img_away)

        btnFav = findViewById(R.id.btn_add_fav)

        btnFav.setOnClickListener {
            if (!isFav) {
                addtoFavorite(matchData)
                isFav = !isFav
                setFavorite()
            } else {
                removeFavorite(matchData.idEvent.toString())
                isFav = !isFav
                setFavorite()
            }
        }

        setFavorite()
        btnFav.visibility = View.VISIBLE
    }

    private fun addtoFavorite(data: DbMatchModel) {
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

    private fun favState(idEvent: String) {
        database.use {
            val get = select(DbMatchUtil.TABLE_FAVORITE).whereArgs("(id = {id})", "id" to idEvent)
            val favorites = get.parseList(classParser<DbMatchModel>())
            if (favorites.isNotEmpty()) {
                isFav = true
            }
        }
    }

}
