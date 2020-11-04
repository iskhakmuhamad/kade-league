package com.example.myleaguemvp.model.league

import com.google.gson.annotations.SerializedName

data class LeagueResponse(

    @field:SerializedName("leagues")
    val leagues: List<LeaguesItem>
)