package com.example.travelapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.travelapp.ui.nav.AppDrawerContent
import com.example.travelapp.ui.nav.Routes
import com.example.travelapp.ui.nav.bottomTabs
import com.example.travelapp.ui.screen.ExploreScreen
import com.example.travelapp.ui.screen.PlaceholderScreen
import com.example.travelapp.ui.screen.ProfileScreen
import com.example.travelapp.ui.screen.ResultsScreen
import com.example.travelapp.ui.screen.TripsScreen
import com.example.travelapp.ui.screen.WatchScreen
import com.example.travelapp.ui.theme.BrandBlue
import com.example.travelapp.ui.theme.BrandBlueSoft
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AirplaneTicket
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Flight
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Settings
import kotlinx.coroutines.launch

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val searchState = rememberSearchState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Routes.EXPLORE

    val showBottomNav = currentRoute in setOf(Routes.EXPLORE, Routes.TRIPS, Routes.WATCH, Routes.PROFILE)

    val openDrawer: () -> Unit = { scope.launch { drawerState.open() } }
    val closeDrawer: () -> Unit = { scope.launch { drawerState.close() } }

    val navigateTab: (String) -> Unit = { route ->
        if (route != currentRoute) {
            navController.navigate(route) {
                popUpTo(Routes.EXPLORE) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    val navigateFromDrawer: (String) -> Unit = { route ->
        closeDrawer()
        if (route != currentRoute) {
            navController.navigate(route) {
                popUpTo(Routes.EXPLORE) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    CompositionLocalProvider(searchState) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = MaterialTheme.colorScheme.surface,
                    drawerTonalElevation = 0.dp
                ) {
                    AppDrawerContent(
                        selectedRoute = currentRoute,
                        onClose = closeDrawer,
                        onSelect = navigateFromDrawer,
                        onSignOut = closeDrawer,
                        onGoPremium = closeDrawer
                    )
                }
            }
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f)) {
                        NavHost(navController, startDestination = Routes.EXPLORE) {
                            composable(Routes.EXPLORE) {
                                ExploreScreen(
                                    onMenuClick = openDrawer,
                                    onSearch = { navController.navigate(Routes.RESULTS) },
                                    onViewAllDeals = { navController.navigate(Routes.DEALS) }
                                )
                            }
                            composable(Routes.RESULTS) {
                                ResultsScreen(onBack = { navController.popBackStack() })
                            }
                            composable(Routes.TRIPS) {
                                TripsScreen(onMenuClick = openDrawer)
                            }
                            composable(Routes.WATCH) {
                                WatchScreen(onMenuClick = openDrawer)
                            }
                            composable(Routes.PROFILE) {
                                ProfileScreen(onMenuClick = openDrawer)
                            }
                            composable(Routes.DEALS) {
                                PlaceholderScreen(
                                    title = "Deals",
                                    blurb = "Daily flight deals tailored to you. Coming soon.",
                                    icon = Icons.Outlined.LocalOffer,
                                    onMenuClick = openDrawer
                                )
                            }
                            composable(Routes.ALERTS) {
                                PlaceholderScreen(
                                    title = "Price Alerts",
                                    blurb = "Get a ping when your saved routes drop in price.",
                                    icon = Icons.Outlined.NotificationsNone,
                                    onMenuClick = openDrawer
                                )
                            }
                            composable(Routes.SAVED) {
                                PlaceholderScreen(
                                    title = "Saved Flights",
                                    blurb = "Your shortlist of flights you're considering.",
                                    icon = Icons.Outlined.Bookmark,
                                    onMenuClick = openDrawer
                                )
                            }
                            composable(Routes.AIRPORTS) {
                                PlaceholderScreen(
                                    title = "Airports",
                                    blurb = "Browse airports, terminals and travel guides.",
                                    icon = Icons.Outlined.Flight,
                                    onMenuClick = openDrawer
                                )
                            }
                            composable(Routes.AIRLINES) {
                                PlaceholderScreen(
                                    title = "Airlines",
                                    blurb = "Compare airlines, fees, baggage and ratings.",
                                    icon = Icons.Outlined.AirplaneTicket,
                                    onMenuClick = openDrawer
                                )
                            }
                            composable(Routes.SETTINGS) {
                                PlaceholderScreen(
                                    title = "Settings",
                                    blurb = "Currency, units, notifications and account.",
                                    icon = Icons.Outlined.Settings,
                                    onMenuClick = openDrawer
                                )
                            }
                            composable(Routes.HELP) {
                                PlaceholderScreen(
                                    title = "Help & Support",
                                    blurb = "FAQs, chat with support, and travel tips.",
                                    icon = Icons.Outlined.HelpOutline,
                                    onMenuClick = openDrawer
                                )
                            }
                        }
                    }
                    if (showBottomNav) {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 0.dp
                        ) {
                            bottomTabs.forEach { tab ->
                                val selected = currentRoute == tab.route
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = { navigateTab(tab.route) },
                                    icon = {
                                        Icon(
                                            if (selected) tab.iconSelected else tab.icon,
                                            contentDescription = tab.label
                                        )
                                    },
                                    label = { Text(tab.label) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = BrandBlue,
                                        selectedTextColor = BrandBlue,
                                        indicatorColor = BrandBlueSoft,
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CompositionLocalProvider(
    searchState: SearchState,
    content: @Composable () -> Unit
) {
    androidx.compose.runtime.CompositionLocalProvider(LocalSearchState provides searchState, content = content)
}
