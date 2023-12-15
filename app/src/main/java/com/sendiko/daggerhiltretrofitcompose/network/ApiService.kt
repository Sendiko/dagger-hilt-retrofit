package com.sendiko.daggerhiltretrofitcompose.network

import com.sendiko.daggerhiltretrofitcompose.network.responses.GetConcertsResponse
import com.sendiko.daggerhiltretrofitcompose.network.responses.GetTicketsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("tickets")
    fun getTickets(): Call<GetTicketsResponse>

    @GET("concerts")
    fun getConcerts(): Call<GetConcertsResponse>

}