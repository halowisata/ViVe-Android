package academy.bangkit.jetvive.helper

import academy.bangkit.jetvive.R

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