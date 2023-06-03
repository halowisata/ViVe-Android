package academy.bangkit.jetvive

import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.ui.components.BottomBar
import academy.bangkit.jetvive.ui.navigation.Screen
import academy.bangkit.jetvive.ui.screen.bookmark.BookmarkScreen
import academy.bangkit.jetvive.ui.screen.detail.DetailScreen
import academy.bangkit.jetvive.ui.screen.form.FormScreen
import academy.bangkit.jetvive.ui.screen.home.HomeScreen
import academy.bangkit.jetvive.ui.screen.login.LoginScreen
import academy.bangkit.jetvive.ui.screen.onboarding.OnboardingScreen
import academy.bangkit.jetvive.ui.screen.profile.ProfileScreen
import academy.bangkit.jetvive.ui.screen.register.RegisterScreen
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetViVeApp(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            when (currentRoute) {
                Screen.Home.route -> BottomBar(navController = navController)
                Screen.Bookmark.route -> BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Onboarding.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Onboarding.route) {
                OnboardingScreen(
                    navigateToLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Onboarding.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    navigateToSignUp = {
                        navController.navigate(Screen.Register.route)
                    },
                    navigateToForm = {
                        navController.navigate(Screen.Form.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(
                    navigateToSignIn = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Register.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Screen.Form.route) {
                FormScreen(
                    navigateToHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(0)
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    userName = "John Doe",
                    userMood = "Happy",
                    navigateToProfile = {
                        navController.navigate(Screen.Profile.route)
                    },
                    navigateToForm = {
                        navController.navigate(Screen.Form.route)
                    },
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailTouristAttraction.createRoute(id))
                    }
                )
            }
            composable(Screen.Bookmark.route) {
                BookmarkScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    userImage = R.drawable.jetpack_compose,
                    userName = "John Doe",
                    username = "johndoe",
                    userEmail = "johndoe@example.com",
                    userPhoneNumber = "+62 812-3456-7890",
                    userAddress = "Indonesia",
                    userGender = "Man",
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = Screen.DetailTouristAttraction.route,
                arguments = listOf(navArgument("id") {
                    type = NavType.StringType
                }),
                content = {
                    DetailScreen(
                        touristAttractionId = "tourist_attraction-1"
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetViVeAppPreview() {
    JetViVeTheme {
        JetViVeApp()
    }
}