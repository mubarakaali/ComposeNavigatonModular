package com.northsoltech.bikeagb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.northsoltech.bikeagb.navigation.SetupNavGraph
import com.northsoltech.framework.ui.theming.BikeTheme
import org.koin.androidx.compose.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BikeTheme {
              val  navHostController = rememberNavController()
               SetupNavGraph(navController = navHostController)
            }
        }
    }
}