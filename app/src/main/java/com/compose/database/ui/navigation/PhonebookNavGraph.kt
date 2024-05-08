package com.compose.database.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.compose.database.ui.contact.ContactDetailsDestination
import com.compose.database.ui.contact.ContactDetailsScreen
import com.compose.database.ui.contact.ContactEditDestination
import com.compose.database.ui.contact.ContactEditScreen
import com.compose.database.ui.contact.ContactEntryDestination
import com.compose.database.ui.contact.ContactEntryScreen
import com.compose.database.ui.home.HomeDestination
import com.compose.database.ui.home.HomeScreen


/**
 * Provides Navigation graph for the application.
 */
@Composable
fun PhonebookNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToContactEntry = { navController.navigate(ContactEntryDestination.route) },
                navigateToContactUpdate = {
                    navController.navigate("${ContactDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = ContactEntryDestination.route) {
            ContactEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = ContactDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ContactDetailsDestination.contactIdArg) {
                type = NavType.IntType
            })
        ) {
            ContactDetailsScreen(
                navigateToEditContact = { navController.navigate("${ContactEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = ContactEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ContactEditDestination.contactIdArg) {
                type = NavType.IntType
            })
        ) {
            ContactEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}