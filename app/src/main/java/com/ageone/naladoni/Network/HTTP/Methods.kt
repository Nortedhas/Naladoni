package com.ageone.naladoni.Network.HTTP


import com.ageone.naladoni.External.HTTP.API.API
import com.ageone.naladoni.SCAG.DataBase

fun API.getCityStocks(cityHashId: String) {
    request(
        mapOf(
            "router" to "getCityStocks",
            "cityHashId" to cityHashId
        )) { jsonObject ->

            parser.parseAnyObject(jsonObject, DataBase.Stock)
        }
}