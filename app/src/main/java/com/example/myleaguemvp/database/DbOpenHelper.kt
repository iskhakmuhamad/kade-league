package com.example.myleaguemvp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.myleaguemvp.Util.DbMatchUtil
import com.example.myleaguemvp.Util.DbTeamUtil
import org.jetbrains.anko.db.*

class DbOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "Favorite.db", null, 1) {

    companion object {
        private var instace: DbOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): DbOpenHelper {
            if (instace == null) {
                instace = DbOpenHelper(context.applicationContext)
            }
            return instace as DbOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            DbMatchUtil.TABLE_FAVORITE, true,
            DbMatchUtil.ID to TEXT + PRIMARY_KEY + NOT_NULL,
            DbMatchUtil.LEAGUE_NAME to TEXT,
            DbMatchUtil.EVENT_MATCH to TEXT,
            DbMatchUtil.HOME_TEAM to TEXT,
            DbMatchUtil.AWAY_TEAM to TEXT,
            DbMatchUtil.DATE_EVENT to TEXT,
            DbMatchUtil.TIME_EVENT to TEXT,
            DbMatchUtil.HOME_SCORE to TEXT,
            DbMatchUtil.AWAY_SCORE to TEXT,
            DbMatchUtil.HOME_BADGE to TEXT,
            DbMatchUtil.AWAY_BADGE to TEXT,
            DbMatchUtil.SEASON to TEXT,
            DbMatchUtil.TIPE to TEXT
        )
        db?.createTable(
            DbTeamUtil.TABLE_TEAM, true,
            DbTeamUtil.ID to TEXT + PRIMARY_KEY + NOT_NULL,
            DbTeamUtil.LEAGUE_NAME to TEXT,
            DbTeamUtil.TEAM_NAME to TEXT,
            DbTeamUtil.DESCRIPTION to TEXT,
            DbTeamUtil.ESTABILISHED to TEXT,
            DbTeamUtil.BADGE to TEXT,
            DbTeamUtil.SPORT_TIPE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(DbMatchUtil.TABLE_FAVORITE, true)
        db?.dropTable(DbTeamUtil.TABLE_TEAM, true)
    }
}

val Context.database: DbOpenHelper
    get() = DbOpenHelper.getInstance(applicationContext)