package com.sendiko.daggerhiltretrofitcompose.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material.icons.rounded.Stadium
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.daggerhiltretrofitcompose.network.responses.Ticket

@Composable
fun TicketCard(
    modifier: Modifier = Modifier,
    ticket: Ticket
) {
    OutlinedCard(
        modifier = modifier.width(400.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = ticket.concertName.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Card {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AirplaneTicket,
                            contentDescription = null
                        )
                        Text(
                            text = ticket.ticketNumber.toString(),
                            fontStyle = Italic,
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Stadium,
                        contentDescription = null
                    )
                    Text(
                        text = ticket.stage.toString(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = ticket.concertDate.toString(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
            Card {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = ticket.concertTime.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun TicketCardPrev() {
    Surface {
        TicketCard(
            modifier = Modifier.fillMaxWidth(),
            ticket = Ticket(
                concertName = "Sendiko Code Fest",
                ticketNumber = "#564654564",
                stage = "Gelora Bung Karno",
                concertDate = "2023-01-23",
                concertTime = "18:30"
            )
        )

    }
}