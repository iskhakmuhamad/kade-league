package com.example.myleaguemvp.model.match

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @field:SerializedName("events")
    val events: List<MatchItems>,
    @field:SerializedName("event")
    val event: List<MatchItems>
)