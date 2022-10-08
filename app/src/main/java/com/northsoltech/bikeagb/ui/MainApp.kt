///*
// * Copyright 2022 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.northsoltech.bikeagb.ui
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Scaffold
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.windowsizeclass.WindowSizeClass
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.semantics.semantics
//import androidx.compose.ui.semantics.testTagsAsResourceId
//import com.northsoltech.bikeagb.navigation.MainDestination
//import com.northsoltech.bikeagb.navigation.MainNavHost
//import com.northsoltech.framework.ui.theming.BikeTheme
//
//@OptIn(
//    ExperimentalMaterial3Api::class,
//    ExperimentalLayoutApi::class,
//    ExperimentalComposeUiApi::class
//)
//@Composable
//fun MainApp(
//    windowSizeClass: WindowSizeClass,
//    appState: MainAppState = rememberNiaAppState(windowSizeClass)
//) {
//    BikeTheme {
////        val background: @Composable (@Composable () -> Unit) -> Unit =
////            when (appState.currentDestination?.route) {
////                MainDestination.route -> { content -> NiaGradientBackground(content = content) }
////                else -> { content -> NiaBackground(content = content) }
////            }
//
////        background {
//            Scaffold(
//                modifier = Modifier.semantics {
//                    testTagsAsResourceId = true
//                },
////                containerColor = Color.Transparent,
//                contentColor = MaterialTheme.colors.onBackground,
////                contentWindowInsets = WindowInsets(0, 0, 0, 0),
////                bottomBar = {
////                    if (appState.shouldShowBottomBar) {
////                        NiaBottomBar(
////                            destinations = appState.topLevelDestinations,
////                            onNavigateToDestination = appState::navigate,
////                            currentDestination = appState.currentDestination
////                        )
////                    }
////                }
//            ) { padding ->
//                Row(
//                    Modifier
//                        .fillMaxSize()
//                        .windowInsetsPadding(
//                            WindowInsets.safeDrawing.only(
//                                WindowInsetsSides.Horizontal
//                            )
//                        )
//                ) {
////                    if (appState.shouldShowNavRail) {
////                        NiaNavRail(
////                            destinations = appState.topLevelDestinations,
////                            onNavigateToDestination = appState::navigate,
////                            currentDestination = appState.currentDestination,
////                            modifier = Modifier.safeDrawingPadding()
////                        )
////                    }
//
//                    MainNavHost(
//                        navController = appState.navController,
//                        onBackClick = appState::onBackClick,
//                        onNavigateToDestination = appState::navigate,
//                        modifier = Modifier
//                            .padding(padding)
//                            .consumedWindowInsets(padding)
//                    )
//                }
//            }
////        }
//    }
//}
//
////@Composable
////private fun NiaNavRail(
////    destinations: List<TopLevelDestination>,
////    onNavigateToDestination: (TopLevelDestination) -> Unit,
////    currentDestination: NavDestination?,
////    modifier: Modifier = Modifier,
////) {
////    NiaNavigationRail(modifier = modifier) {
////        destinations.forEach { destination ->
////            val selected =
////                currentDestination?.hierarchy?.any { it.route == destination.route } == true
////            NiaNavigationRailItem(
////                selected = selected,
////                onClick = { onNavigateToDestination(destination) },
////                icon = {
////                    val icon = if (selected) {
////                        destination.selectedIcon
////                    } else {
////                        destination.unselectedIcon
////                    }
////                    when (icon) {
////                        is ImageVectorIcon -> Icon(
////                            imageVector = icon.imageVector,
////                            contentDescription = null
////                        )
////                        is DrawableResourceIcon -> Icon(
////                            painter = painterResource(id = icon.id),
////                            contentDescription = null
////                        )
////                    }
////                },
////                label = { Text(stringResource(destination.iconTextId)) }
////            )
////        }
////    }
////}
////
////@Composable
////private fun NiaBottomBar(
////    destinations: List<TopLevelDestination>,
////    onNavigateToDestination: (TopLevelDestination) -> Unit,
////    currentDestination: NavDestination?
////) {
////    NiaNavigationBar {
////        destinations.forEach { destination ->
////            val selected =
////                currentDestination?.hierarchy?.any { it.route == destination.route } == true
////            NiaNavigationBarItem(
////                selected = selected,
////                onClick = { onNavigateToDestination(destination) },
////                icon = {
////                    val icon = if (selected) {
////                        destination.selectedIcon
////                    } else {
////                        destination.unselectedIcon
////                    }
////                    when (icon) {
////                        is ImageVectorIcon -> Icon(
////                            imageVector = icon.imageVector,
////                            contentDescription = null
////                        )
////                        is DrawableResourceIcon -> Icon(
////                            painter = painterResource(id = icon.id),
////                            contentDescription = null
////                        )
////                    }
////                },
////                label = { Text(stringResource(destination.iconTextId)) }
////            )
////        }
////    }
////}
