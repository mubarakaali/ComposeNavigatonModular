package com.northsoltech.sign.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.northsoltech.sign.ui.events.SignInTopEvents
import com.northsoltech.sign.ui.signup.UserTypeRoutes
import com.northsoltech.sign.ui.signin.LoginScreen
import com.northsoltech.sign.ui.signup.SignupRoutes

const val SIGN_GRAPH_ROUTE = "sign_routes"
internal const val HOME_GRAPH_ROUTE = "home_routes"
fun NavGraphBuilder.signNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = SignDestinations.Login.route,
        route = SIGN_GRAPH_ROUTE
    ){
        composable(route = SignDestinations.Login.route) {
            LoginScreen{
                when(it){
                    is SignInTopEvents.SignInEvents->{
                        navController.navigate(
                            route = HOME_GRAPH_ROUTE
                        )
                    }
                    is SignInTopEvents.RegistrationEvents->{
                        navController.navigate(
                            route = SignDestinations.UserType.route
                        )
                    }
                    else->{
                        navController.navigate(
                            route = HOME_GRAPH_ROUTE
                        )
                    }
                }
            }
        }

        composable(route = SignDestinations.UserType.route) {
            UserTypeRoutes(navHostController = navController)
        }
        composable(route = SignDestinations.Signup.route) {
            SignupRoutes(navController = navController)
        }
    }

}