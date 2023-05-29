package academy.bangkit.jetvive

import academy.bangkit.jetvive.ui.navigation.Screen
import academy.bangkit.jetvive.ui.screen.login.LoginScreen
import academy.bangkit.jetvive.ui.screen.onboarding.OnboardingScreen
import academy.bangkit.jetvive.ui.screen.register.RegisterScreen
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun JetViVeApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface() {
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