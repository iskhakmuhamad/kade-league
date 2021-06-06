package com.example.myleaguemvp.model.standing

import com.google.gson.annotations.SerializedName

data class StandingItem(

    @field:SerializedName("intLoss")
    val loss: Int? = null,

    @field:SerializedName("intPoints")
    val total: Int? = null,

    @field:SerializedName("intGoalsFor")
    val goalsfor: Int? = null,

    @field:SerializedName("intGoalsAgainst")
    val goalsagainst: Int? = null,

    @field:SerializedName("idTeam")
    val teamid: String? = null,

    @field:SerializedName("intGoalDifference")
    val goalsdifference: Int? = null,

    @field:SerializedName("strTeam")
    val name: String? = null,

    @field:SerializedName("intDraw")
    val draw: Int? = null,

    @field:SerializedName("intPlayed")
    val played: Int? = null,

    @field:SerializedName("intWin")
    val win: Int? = null
)
