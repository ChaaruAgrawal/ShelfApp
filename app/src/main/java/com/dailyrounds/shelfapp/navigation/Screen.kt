package com.dailyrounds.shelfapp.navigation

/**
 * screen routes for all screens
 */
sealed class Screen(val route: String) {
    object SignIn: Screen("signin")
    object SignUp: Screen("signup")
    object BookShelf: Screen("bookshelf")
    object BookDetail: Screen("bookdetail")
}
