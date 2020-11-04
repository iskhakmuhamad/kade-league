package com.example.myleaguemvp.model.match

import com.google.gson.annotations.SerializedName

data class MatchItems(

    @field:SerializedName("strSport")
    val strSport: String? = null,

    @field:SerializedName("idLeague")
    val idLeague: String? = null,

    @field:SerializedName("idEvent")
    val idEvent: String? = null,

    @field:SerializedName("idHomeTeam")
    val idHomeTeam: String? = null,

    @field:SerializedName("intHomeScore")
    val intHomeScore: Any? = null,

    @field:SerializedName("dateEvent")
    val dateEvent: String? = null,

    @field:SerializedName("strAwayTeam")
    val strAwayTeam: String? = null,

    @field:SerializedName("idAwayTeam")
    val idAwayTeam: String? = null,

    @field:SerializedName("strDescriptionEN")
    val strDescriptionEN: Any? = null,

    @field:SerializedName("strTime")
    val strTime: String? = null,

    @field:SerializedName("strSeason")
    val strSeason: String? = null,

    @field:SerializedName("strEvent")
    val strEvent: String? = null,

    @field:SerializedName("strHomeTeam")
    val strHomeTeam: String? = null,

    @field:SerializedName("strLeague")
    val strLeague: String? = null,

    @field:SerializedName("intAwayScore")
    val intAwayScore: Any? = null

)