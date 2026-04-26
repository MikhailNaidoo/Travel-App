package com.example.travelapp.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AirplaneTicket
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Flight
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

object Routes {
    const val EXPLORE = "explore"
    const val RESULTS = "results"
    const val TRIPS = "trips"
    const val WATCH = "watch"
    const val PROFILE = "profile"
    const val DEALS = "deals"
    const val ALERTS = "alerts"
    const val SAVED = "saved"
    const val AIRPORTS = "airports"
    const val AIRLINES = "airlines"
    const val SETTINGS = "settings"
    const val HELP = "help"
}

data class BottomTab(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val iconSelected: ImageVector
)

val bottomTabs = listOf(
    BottomTab(Routes.EXPLORE, "Explore", Icons.Outlined.Search, Icons.Filled.Search),
    BottomTab(Routes.TRIPS, "Trips", Icons.Outlined.WorkOutline, Icons.Filled.WorkOutline),
    BottomTab(Routes.WATCH, "Watch", Icons.Outlined.PlayCircle, Icons.Filled.PlayCircle),
    BottomTab(Routes.PROFILE, "Profile", Icons.Outlined.Person, Icons.Filled.Person)
)

data class DrawerItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val drawerPrimary = listOf(
    DrawerItem(Routes.EXPLORE, "Explore", Icons.Outlined.Search),
    DrawerItem(Routes.TRIPS, "Trips", Icons.Outlined.WorkOutline),
    DrawerItem(Routes.WATCH, "Watch", Icons.Outlined.PlayCircle),
    DrawerItem(Routes.DEALS, "Deals", Icons.Outlined.LocalOffer),
    DrawerItem(Routes.ALERTS, "Price Alerts", Icons.Outlined.NotificationsNone),
    DrawerItem(Routes.SAVED, "Saved Flights", Icons.Outlined.Bookmark)
)

val drawerSecondary = listOf(
    DrawerItem(Routes.AIRPORTS, "Airports", Icons.Outlined.Flight),
    DrawerItem(Routes.AIRLINES, "Airlines", Icons.Outlined.AirplaneTicket),
    DrawerItem(Routes.SETTINGS, "Settings", Icons.Outlined.Settings),
    DrawerItem(Routes.HELP, "Help & Support", Icons.Outlined.HelpOutline)
)
