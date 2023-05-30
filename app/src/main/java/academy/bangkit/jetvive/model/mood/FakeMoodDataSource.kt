package academy.bangkit.jetvive.model.mood

import academy.bangkit.jetvive.R

object FakeMoodDataSource {
    val dummyMoods = listOf(
        Mood("mood-1", "Happy", R.drawable.happy, 0xFFE5F4F6),
        Mood("mood-2", "Sad", R.drawable.sad, 0xFFFDEBEA),
        Mood("mood-3", "Calm", R.drawable.calm, 0xFFE5F1FD),
        Mood("mood-4", "Angry", R.drawable.angry, 0xFFFFECF2)
    )
}