package com.sendiko.daggerhiltretrofitcompose.ui.screens

sealed class HomeScreenEvents {
    data object OnTicketsLoad: HomeScreenEvents()
    data object OnConcertLoad: HomeScreenEvents()
}