package social.bondoo.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import social.bondoo.movieapp.screens.home.HomeScreen
import social.bondoo.movieapp.screens.details.DetailsScreen

//导航控制器
@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {

        composable(MovieScreens.HomeScreen.name) {
            // here we pass where this should lead us to
            HomeScreen(navController = navController)
        }

        //www.google.com/detail-screen/id=1
        composable(
            MovieScreens.DetailsScreen.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
        ) {
            backStackEntry ->
            DetailsScreen(navController = navController,
                backStackEntry.arguments?.getString("movie"))
        }
    }
}


