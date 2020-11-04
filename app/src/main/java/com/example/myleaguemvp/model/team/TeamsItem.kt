package com.example.myleaguemvp.model.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamsItem(

    @field:SerializedName("idTeam")
    val idTeam: String? = null,

    @field:SerializedName("strLeague")
    val strLeague: String? = null,

    @field:SerializedName("strTeam")
    val strTeam: String? = null,

    @field:SerializedName("strDescriptionEN")
    val strDescriptionEN: String? = null,

    @field:SerializedName("intFormedYear")
    val intFormedYear: String? = null,

    @field:SerializedName("strTeamBadge")
    val strTeamBadge: String? = null,

    @field:SerializedName("strSport")
    val strSport: String? = null
) : Parcelable