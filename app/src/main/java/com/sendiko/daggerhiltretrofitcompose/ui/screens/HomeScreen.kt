package com.sendiko.daggerhiltretrofitcompose.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.daggerhiltretrofitcompose.network.responses.Concert
import com.sendiko.daggerhiltretrofitcompose.network.responses.Ticket
import com.sendiko.daggerhiltretrofitcompose.ui.component.ConcertCard
import com.sendiko.daggerhiltretrofitcompose.ui.component.TicketCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeScreenState,
    onEvents: (HomeScreenEvents) -> Unit
) {
    LaunchedEffect(
        key1 = state,
        block = {
            if (state.tickets == emptyList<Ticket>()){
                onEvents(HomeScreenEvents.OnTicketsLoad)
            }
            if (state.concerts == emptyList<Concert>()){
                onEvents(HomeScreenEvents.OnConcertLoad)
            }
        }
    )
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Looking for something fun?") },
                actions = {
                    Card(
                        shape = CircleShape,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                item {
                    AnimatedVisibility(visible = state.isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                            content = {
                                CircularProgressIndicator()
                            }
                        )
                    }
                }
                item {
                    Text(
                        text = "Your tickets: ",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            items(state.tickets){ ticket ->
                                TicketCard(ticket = ticket!!)
                            }
                        }
                    )
                }
                item {
                    Text(
                        text = "Concert on going",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
                items(state.concerts){ concert ->
                    ConcertCard(concert = concert!!)
                }
            }
        )
    }
}