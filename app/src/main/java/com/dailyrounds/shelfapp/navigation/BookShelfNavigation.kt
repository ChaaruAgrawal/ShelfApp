package com.dailyrounds.shelfapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dailyrounds.shelfapp.ui.authentication.SignInHome
import com.dailyrounds.shelfapp.ui.authentication.SignUpHome
import com.dailyrounds.shelfapp.ui.bookshelf.BookDetailHome
import com.dailyrounds.shelfapp.ui.bookshelf.BookShelfHome
import com.dailyrounds.shelfapp.ui.bookshelf.BookShelfViewModel

/**
 * navigation graph for the application
 */
@Composable
fun BookShelfNavigation(modifier: Modifier = Modifier, navController: NavHostController, bookShelfViewModel: BookShelfViewModel = hiltViewModel()) {
    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
        composable(route = Screen.SignIn.route) {
            SignInHome(navigateToBookShelf = { navController.navigate(Screen.BookShelf.route) }) {
                navController.navigate(Screen.SignUp.route)
            }
        }
        composable(route = Screen.SignUp.route) {
            SignUpHome { navController.navigateUp() }
        }
        composable(route = Screen.BookShelf.route) {
            BookShelfHome(modifier, bookShelfViewModel, { navController.navigate(Screen.BookDetail.route) }) {
                navController.navigate(Screen.SignIn.route)
                navController.clearBackStack(Screen.SignIn.route)
            }
        }
        composable(route = Screen.BookDetail.route) {
            BookDetailHome(modifier, bookShelfViewModel)
        }
    }
}
