package academy.bangkit.jetvive.helper

import academy.bangkit.jetvive.R
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

fun getPeriod(hour: Int) = when (hour) {
    in 6..11 -> {
        R.string.morning
    }
    in 12..16 -> {
        R.string.afternoon
    }
    in 17..20 -> {
        R.string.evening
    }
    in 21..23 -> {
        R.string.night
    }
    else -> {
        R.string.morning
    }
}

sealed class BackPress {
    object Idle : BackPress()
    object InitialTouch : BackPress()
}

@Composable
fun TwiceBackPressExit() {
    var showToast by remember { mutableStateOf(false) }

    var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
    val context = LocalContext.current

    if(showToast){
        Toast.makeText(context, R.string.press_back_again_to_exit, Toast.LENGTH_SHORT).show()
        showToast= false
    }


    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(2000)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.InitialTouch
        showToast = true
    }
}