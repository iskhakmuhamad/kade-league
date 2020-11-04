package com.example.myleaguemvp.model.standing

import com.google.gson.annotations.SerializedName

data class StandingsResponse(

    @field:SerializedName("table")
    val table: List<StandingItem>
)