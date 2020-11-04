package com.example.myleaguemvp.model.team

import com.google.gson.annotations.SerializedName

data class TeamResponse(

    @field:SerializedName("teams")
    val teams: List<TeamsItem>
)