package com.example.myleaguemvp.network

object SportDbApi {

    const val BASE_URL = "https://www.thesportsdb.com/"

    fun getDetailLeague(id: String): String {
        return BASE_URL + "api/v1/json/1/lookupleague.php?id=" + id
    }

    fun getNextMatch(id: String): String {
        return BASE_URL + "api/v1/json/1/eventsnextleague.php?id=" + id
    }

    fun getLastMatch(id: String): String {
        return BASE_URL + "api/v1/json/1/eventspastleague.php?id=" + id
    }

    fun getMatchDetail(id: String): String {
        return BASE_URL + "api/v1/json/1/lookupevent.php?id=" + id
    }

    fun getSearchMatch(query: String): String {
        return BASE_URL + "api/v1/json/1/searchevents.php?e=" + query
    }

    fun getTeamList(id: String): String {
        return BASE_URL + "api/v1/json/1/lookup_all_teams.php?id=" + id
    }

    fun getTeamDetail(id: String): String {
        return BASE_URL + "api/v1/json/1/lookupteam.php?id=" + id
    }

    fun getSearchTeam(t: String): String {
        return BASE_URL + "api/v1/json/1/searchteams.php?t=" + t
    }

    fun getStanding(id: String): String {
        return BASE_URL + "api/v1/json/1/lookuptable.php?l=" + id
    }
}