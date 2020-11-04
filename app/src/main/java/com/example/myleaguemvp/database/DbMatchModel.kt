package com.example.myleaguemvp.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DbMatchModel(
    val idEvent: String?,
    val leagueName: String?,
    val eventMatch: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val dateEvent: String?,
    val timeEvent: String?,
    val homeScore: String?,
    val awayScore: String?,
    var homeBadge: String?,
    var awayBadge: String?,
    val season: String?,
    var tipe: String = "next"
) : Parcelable