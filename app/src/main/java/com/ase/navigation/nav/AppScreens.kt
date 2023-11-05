package com.ase.navigation.nav

sealed class AppScreens(val route: String) {

    object Home : AppScreens("home")
    object Billboard : AppScreens("billboard")
    object Profile : AppScreens("profile")

    object Library : AppScreens("library") {
        object List : AppScreens("list")
        object Detail : AppScreens("detail")
    }

}
