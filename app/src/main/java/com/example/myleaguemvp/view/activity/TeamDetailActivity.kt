package com.example.myleaguemvp.view.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.DbTeamUtil
import com.example.myleaguemvp.Util.KeyUtil
import com.example.myleaguemvp.database.database
import com.example.myleaguemvp.model.team.TeamsItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailActivity : AppCompatActivity() {

    private lateinit var btnFav: FloatingActionButton
    private var isFav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        val team = intent.getParcelableExtra<TeamsItem>(KeyUtil.KEYTEAM)
        btnFav = findViewById(R.id.btn_fav)

        if (team != null) {

            favState(team.idTeam.toString())
            setFavorite()

            tv_team_name.text = team.strTeam
            tv_estabilished.text = team.intFormedYear.toString()
            tv_team_league.text = team.strLeague
            tv_team_description.text = team.strDescriptionEN

            Glide.with(this)
                .load(team.strTeamBadge)
                .placeholder(R.drawable.ic_loading_24dp)
                .into(img_team_detail)
            btnFav.setOnClickListener {
                if (isFav) {
                    removeFavorite(team.idTeam.toString())
                    isFav = !isFav
                    setFavorite()
                } else {
                    addFavorite(team)
                    isFav = !isFav
                    setFavorite()
                }
            }

        }
    }

    fun addFavorite(data: TeamsItem) {
        try {
            database.use {
                insert(
                    DbTeamUtil.TABLE_TEAM,
                    DbTeamUtil.ID to data.idTeam,
                    DbTeamUtil.LEAGUE_NAME to data.strLeague,
                    DbTeamUtil.TEAM_NAME to data.strTeam,
                    DbTeamUtil.DESCRIPTION to data.strDescriptionEN,
                    DbTeamUtil.ESTABILISHED to data.intFormedYear.toString(),
                    DbTeamUtil.BADGE to data.strTeamBadge,
                    DbTeamUtil.SPORT_TIPE to data.strSport
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

    private fun removeFavorite(id: String) {
        try {
            database.use {
                delete(DbTeamUtil.TABLE_TEAM, "(id_team = {id})", "id" to id)
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
            val get = select(DbTeamUtil.TABLE_TEAM).whereArgs("(id_team = {id})", "id" to idfav)
            val favorites = get.parseList(classParser<TeamsItem>())
            if (favorites.isNotEmpty()) {
                isFav = true
            }
        }
    }

}

