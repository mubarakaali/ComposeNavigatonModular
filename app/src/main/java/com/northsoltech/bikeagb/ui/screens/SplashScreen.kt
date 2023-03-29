package com.northsoltech.bikeagb.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.northsoltech.app.R
import com.northsoltech.bikeagb.ui.SplashViewModel
import com.northsoltech.framework.components.MainTitleText
import com.northsoltech.sign.ui.navigation.SIGN_GRAPH_ROUTE
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun PreviewSplashScreen(){
//    BikeaGbTheme {
//        SplashScreen()
//    }
}

@Composable
fun SplashRoutes(
    navHostController: NavHostController,
    splashVieModel: SplashViewModel = koinViewModel(),
){

    SplashScreen(
        splashVieModel = splashVieModel,
        navHostController = navHostController)
}

@Composable
fun SplashScreen(
       splashVieModel: SplashViewModel,
        navHostController: NavHostController,
) {
        val timeOut =  remember { splashVieModel.isTimeOut}


    if(timeOut.value){
        Log.d("jejeje", "SplashScreen INSIDE: TRUE ")
        timeOut.value = false
        navHostController.navigate(route = SIGN_GRAPH_ROUTE){
            popUpTo(navHostController.graph.findStartDestination().id){
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
       MainTitleText(title = stringResource(id = R.string.app_name))
    }
}