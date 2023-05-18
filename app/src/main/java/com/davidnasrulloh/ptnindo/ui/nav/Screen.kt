package com.davidnasrulloh.ptnindo.ui.nav
sealed class Screen(val route: String) {
    object Beranda : Screen("beranda")
    object Favorite : Screen("favorite")
    object About : Screen("about")
    object Detail : Screen("beranda/{univId}"){
        fun createRoute(univId: Int) = "beranda/$univId"
    }
}