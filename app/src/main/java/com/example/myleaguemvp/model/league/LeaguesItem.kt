package com.example.myleaguemvp.model.league

import com.google.gson.annotations.SerializedName

data class LeaguesItem(

    @field:SerializedName("dateFirstEvent")
    val dateFirstEvent: String? = null,

    @field:SerializedName("intFormedYear")
    val intFormedYear: String? = null,

    @field:SerializedName("strSport")
    val strSport: String? = null,

    @field:SerializedName("strDescriptionEN")
    val strDescriptionEN: String? = null,

    @field:SerializedName("strComplete")
    val strComplete: String? = null,

    @field:SerializedName("idLeague")
    val idLeague: String? = null,


    @field:SerializedName("strBadge")
    val strBadge: String? = null,

    @field:SerializedName("strLeague")
    val strLeague: String? = null

)