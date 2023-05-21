package com.davidnasrulloh.ptnindo.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.davidnasrulloh.ptnindo.ui.nav.NavItem
import com.davidnasrulloh.ptnindo.ui.nav.Screen
import com.davidnasrulloh.ptnindo.ui.screen.detailuniv.DetailUnivScreen
import com.davidnasrulloh.ptnindo.ui.theme.PtnindoTheme

@Composable
fun UnivListScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRouteApp = navBackStackEntry?.destination?.route

        Scaffold(
        bottomBar = {
            if(currentRouteApp != Screen.Detail.route){
                BottomBarMenu(navController)
            }
        }, modifier = modifier
    ) { inPadding ->
            NavHost(
                navController = navController ,
                startDestination = Screen.Beranda.route,
                modifier = Modifier.padding(inPadding)
            ) {
                composable(Screen.Beranda.route){

                }
                composable(Screen.Favorite.route){

                }
                composable(Screen.About.route){

                }
                composable(
                    route = Screen.Detail.route,
                    arguments = listOf(
                        navArgument("univId") {
                            type = NavType.IntType
                        }
                    )
                ) {
                    val univId = it.arguments?.getInt("univId") ?: -1
                    DetailUnivScreen()
                }
            }
    }


}


@Composable
fun BottomBarMenu(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    BottomNavigation(
        backgroundColor = Color.Cyan,
        modifier = modifier
            .shadow(24.dp)
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRouteApp = navBackStackEntry?.destination?.route
        val navItems = listOf(
            NavItem(
                title = "Beranda",
                iconNav = Icons.Default.Home,
                screen = Screen.Beranda,
                contentDescription = "beranda"
            ),
            NavItem(
                title = "My Favorite",
                iconNav = Icons.Default.Favorite,
                screen = Screen.Favorite,
                contentDescription = "my_favorite"
            ),
            NavItem(
                title = "About Me",
                iconNav = Icons.Default.Person,
                screen = Screen.About,
                contentDescription = "about_page"
            )
        )
        BottomNavigation(
            backgroundColor = Color.White
        ) {
            navItems.map { itemNav ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = itemNav.iconNav ,
                            contentDescription = itemNav.title
                        )
                    },
                    label = { Text(itemNav.title)},
                    selected = currentRouteApp == itemNav.screen.route,
                    selectedContentColor = MaterialTheme.colors.primaryVariant,
                    unselectedContentColor = Color.Gray,
                    onClick = {
                        navController.navigate(itemNav.screen.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

//data class NavItem(
//    val title: String,
//    val iconNav: ImageVector,
//    val screen: Screen,
//    val contentDescription: String
//)


@Preview(showBackground = true)
@Composable
fun UnivListScreenPreview(){
    PtnindoTheme {
        UnivListScreen()
    }
}

