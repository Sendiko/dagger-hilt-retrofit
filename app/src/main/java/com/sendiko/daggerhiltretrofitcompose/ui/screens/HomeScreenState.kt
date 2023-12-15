package com.sendiko.daggerhiltretrofitcompose.ui.screens

import com.sendiko.daggerhiltretrofitcompose.helper.FailedState
import com.sendiko.daggerhiltretrofitcompose.network.responses.Concert
import com.sendiko.daggerhiltretrofitcompose.network.responses.Ticket

data class HomeScreenState(
    val tickets: List<Ticket?> = emptyList(),
    val concerts: List<Concert?> = emptyList(),
    val isLoading: Boolean = false,
    val failedState: FailedState = FailedState()
)
