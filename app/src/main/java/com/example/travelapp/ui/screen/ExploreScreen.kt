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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.travelapp.data.MockData
import com.example.travelapp.data.model.CityDeal
import com.example.travelapp.data.model.TripType
import com.example.travelapp.ui.LocalSearchState
import com.example.travelapp.ui.components.FlightCard
import com.example.travelapp.ui.components.OutlinedField
import com.example.travelapp.ui.components.SectionHeader
import com.example.travelapp.ui.theme.BrandBlue
import com.example.travelapp.ui.theme.SkyHorizon
import com.example.travelapp.ui.theme.SkyMid
import com.example.travelapp.ui.theme.SkyTop

@Composable
fun ExploreScreen(
    onMenuClick: () -> Unit,
    onSearch: () -> Unit,
    onViewAllDeals: () -> Unit
) {
    val search = LocalSearchState.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            HeroWithSearchCard(
                onMenuClick = onMenuClick,
                onSearch = onSearch
            )
        }
        item { Spacer(Modifier.height(20.dp)) }
        item {
            SectionHeader(
                title = "Top deals from ${search.from.city}",
                action = {
                    Text(
                        "View all",
                        style = MaterialTheme.typography.labelLarge,
                        color = BrandBlue,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { onViewAllDeals() }
                    )
                }
            )
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(MockData.topDeals) { deal ->
                    DealCard(deal = deal)
                }
            }
        }
        item { Spacer(Modifier.height(16.dp)) }
        item {
            SectionHeader(
                title = "Best departing flights",
                action = {
                    Text(
                        "Price",
                        style = MaterialTheme.typography.labelLarge,
                        color = BrandBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
        }
        items(MockData.bestDepartingFlights) { flight ->
            FlightCard(
                flight = flight,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable { onSearch() }
            )
        }
    }
}

@Composable
private fun HeroWithSearchCard(
    onMenuClick: () -> Unit,
    onSearch: () -> Unit
) {
    val search = LocalSearchState.current
    Box(modifier = Modifier.fillMaxWidth()) {
        // Hero gradient background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(SkyTop, SkyMid, SkyHorizon.copy(alpha = 0.85f))
                    )
                )
        ) {
            // soft cloud highlights with radial-ish gradients via overlays
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color.White.copy(alpha = 0.18f), Color.Transparent),
                            radius = 600f
                        )
                    )
            )
        }

        Column(modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.statusBars)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onMenuClick) {
                    Icon(Icons.Outlined.Menu, contentDescription = "Open menu", tint = Color.White)
                }
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Icon(Icons.Outlined.NotificationsNone, contentDescription = "Notifications", tint = Color.White)
                }
            }
            Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
                Text(
                    "Explore the world",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "Find the best flights",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.92f)
                )
            }
            Spacer(Modifier.height(24.dp))
            SearchCard(onSearch = onSearch)
        }
    }
}

@Composable
private fun SearchCard(onSearch: () -> Unit) {
    val search = LocalSearchState.current
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TripTypeTabs(
                selected = search.tripType,
                onSelect = { search.tripType = it }
            )
            Spacer(Modifier.height(16.dp))

            // From / To row with swap button overlay
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedField(
                        label = "From",
                        value = "${search.from.city} (${search.from.code})",
                        leading = Icons.Outlined.LocationOn,
                        leadingTint = BrandBlue,
                        trailing = null,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedField(
                        label = "To",
                        value = "${search.to.city} (${search.to.code})",
                        leading = Icons.Outlined.LocationOn,
                        leadingTint = BrandBlue,
                        trailing = null,
                        modifier = Modifier.weight(1f)
                    )
                }
                // Swap button centered between the two
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
                        .clickable { search.swap() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Outlined.SwapHoriz,
                        contentDescription = "Swap",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedField(
                    label = "Depart",
                    value = search.depart,
                    leading = Icons.Outlined.CalendarMonth,
                    leadingTint = BrandBlue,
                    trailing = null,
                    modifier = Modifier.weight(1f)
                )
                if (search.tripType == TripType.RoundTrip) {
                    OutlinedField(
                        label = "Return",
                        value = search.ret ?: "—",
                        leading = Icons.Outlined.CalendarMonth,
                        leadingTint = BrandBlue,
                        trailing = null,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedField(
                    label = "Travelers",
                    value = search.travelers.toString(),
                    leading = Icons.Outlined.Person,
                    leadingTint = BrandBlue,
                    modifier = Modifier.weight(1f)
                )
                OutlinedField(
                    label = "Class",
                    value = search.cabin.label,
                    leading = Icons.Outlined.WorkspacePremium,
                    leadingTint = BrandBlue,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(28.dp),
                color = BrandBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 52.dp)
                    .clickable { onSearch() }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.Search, contentDescription = null, tint = Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Search flights",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 14.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun TripTypeTabs(selected: TripType, onSelect: (TripType) -> Unit) {
    val tabs = listOf(
        TripType.RoundTrip to "Round trip",
        TripType.OneWay to "One way",
        TripType.MultiCity to "Multi-city"
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        tabs.forEach { (type, label) ->
            val isSelected = type == selected
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSelect(type) }
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    label,
                    style = MaterialTheme.typography.titleSmall,
                    color = if (isSelected) BrandBlue else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                )
                Spacer(Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(if (isSelected) BrandBlue else Color.Transparent)
                )
            }
        }
    }
}

@Composable
private fun DealCard(deal: CityDeal) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.width(180.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.4f)
                    .background(brush = Brush.verticalGradient(deal.gradient))
            ) {
                // subtle highlight overlay for depth
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.White.copy(alpha = 0.15f), Color.Transparent)
                            )
                        )
                )
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    deal.city,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    deal.country,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "from",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "$${deal.priceFromUsd}",
                    style = MaterialTheme.typography.titleLarge,
                    color = com.example.travelapp.ui.theme.PriceGood,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "${deal.stopsLabel} • ${deal.durationLabel}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
