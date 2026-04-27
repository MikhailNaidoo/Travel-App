package com.example.travelapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.travelapp.data.MockData
import com.example.travelapp.ui.LocalSearchState
import com.example.travelapp.ui.StatusBarIcons
import com.example.travelapp.ui.components.FlightCard
import com.example.travelapp.ui.theme.BrandBlue
import com.example.travelapp.ui.theme.PriceGood

@Composable
fun ResultsScreen(onBack: () -> Unit) {
    StatusBarIcons(lightIcons = false)
    val search = LocalSearchState.current
    var selectedDate by remember { mutableIntStateOf(MockData.dateOptions.indexOfFirst { it.isSelected }.coerceAtLeast(0)) }
    var saved by remember { mutableStateOf(false) }
    var selectedFlightIndex by remember { mutableIntStateOf(0) }

    val selectedFlight = MockData.bestDepartingFlights.getOrNull(selectedFlightIndex)

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            item {
                Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Outlined.ArrowBack, contentDescription = "Back")
                        }
                        Spacer(Modifier.weight(1f))
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.Outlined.IosShare,
                                contentDescription = "Share",
                                tint = BrandBlue
                            )
                        }
                        IconButton(onClick = { saved = !saved }) {
                            Icon(
                                if (saved) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Save"
                            )
                        }
                    }
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "${search.from.city} (${search.from.code})",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(Modifier.width(8.dp))
                            Icon(Icons.Outlined.ArrowForward, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "${search.to.city} (${search.to.code})",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "${search.depart}${search.ret?.let { " – $it" } ?: ""} • ${search.travelers} traveler${if (search.travelers == 1) "" else "s"} • ${search.cabin.label}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(MockData.dateOptions) { index, date ->
                        val sel = index == selectedDate
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = BorderStroke(
                                width = if (sel) 1.5.dp else 1.dp,
                                color = if (sel) BrandBlue else MaterialTheme.colorScheme.outline
                            ),
                            modifier = Modifier
                                .clickable { selectedDate = index }
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    date.dayLabel,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = if (sel) BrandBlue else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    "$${date.priceUsd}",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (sel) BrandBlue else MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.SemiBold
                                )
                                if (sel) {
                                    Spacer(Modifier.height(4.dp))
                                    Box(
                                        modifier = Modifier
                                            .height(2.dp)
                                            .width(28.dp)
                                            .background(BrandBlue)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item { Spacer(Modifier.height(16.dp)) }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Best departing flights",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(Modifier.weight(1f))
                    Row(
                        modifier = Modifier.clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Sort",
                            style = MaterialTheme.typography.labelLarge,
                            color = BrandBlue,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.width(4.dp))
                        Icon(Icons.Outlined.Sort, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(18.dp))
                    }
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            itemsIndexed(MockData.bestDepartingFlights) { index, flight ->
                FlightCard(
                    flight = flight,
                    selected = index == selectedFlightIndex,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .clickable { selectedFlightIndex = index }
                )
            }
        }

        // Bottom action bar
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 8.dp,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            tonalElevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "$${selectedFlight?.priceUsd ?: 0}",
                        style = MaterialTheme.typography.titleLarge,
                        color = PriceGood,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "round trip",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Surface(
                    shape = RoundedCornerShape(28.dp),
                    color = BrandBlue,
                    modifier = Modifier
                        .heightIn(min = 48.dp)
                        .clickable { }
                ) {
                    Text(
                        "Select flight",
                        modifier = Modifier.padding(horizontal = 28.dp, vertical = 14.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = androidx.compose.ui.graphics.Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
