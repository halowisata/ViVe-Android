package academy.bangkit.jetvive.model.mood

import academy.bangkit.jetvive.R

object FakeMoodDataSource {
    val dummyMoods = listOf(
        Mood("mood-1", "Happy", R.drawable.mood_happy, 0xFFE5F4F6),
        Mood("mood-2", "Sad", R.drawable.mood_sad, 0xFFFDEBEA),
        Mood("mood-3", "Calm", R.drawable.mood_calm, 0xFFE5F1FD),
        Mood("mood-4", "Angry", R.drawable.mood_angry, 0xFFFFECF2)
    )
}