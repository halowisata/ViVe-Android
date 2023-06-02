package academy.bangkit.jetvive

import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.ui.navigation.Screen
import academy.bangkit.jetvive.ui.screen.detail.DetailScreen
import academy.bangkit.jetvive.ui.screen.home.HomeScreen
import academy.bangkit.jetvive.ui.screen.login.LoginScreen
import academy.bangkit.jetvive.ui.screen.mood.MoodScreen
import academy.bangkit.jetvive.ui.screen.onboarding.OnboardingScreen
import academy.bangkit.jetvive.ui.screen.register.RegisterScreen
import academy.bangkit.jetvive.ui.screen.survey.SurveyScreen
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument

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

    Surface {
        NavHost(
            navController = navController,
            startDestination = Screen.Onboarding.route,
            modifier = Modifier.padding()
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
                    navigateToHome = {
                        navController.navigate(Screen.Mood.route) {
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
            composable(Screen.Mood.route) {
                MoodScreen(
                    navigateToSurvey = {
                        navController.navigate(Screen.Survey.route)
                    }
                )
            }
            composable(Screen.Survey.route) {
                SurveyScreen(
                    navigateToHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(0)
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailTouristAttraction.createRoute(id))
                    }
                )
            }
            composable(
                route = Screen.DetailTouristAttraction.route,
                arguments = listOf(navArgument("id") {
                    type = NavType.StringType
                }),
                content = {
                    DetailScreen(id = "tourist_attraction-1")
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