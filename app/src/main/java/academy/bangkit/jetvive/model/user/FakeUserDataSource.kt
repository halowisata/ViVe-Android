package academy.bangkit.jetvive.model.user

object FakeUserDataSource {
    val dummyUsers = listOf(
        User(
            "user-1",
            "John Doe",
            "johndoe@example.com",
            "supersecretpassword"
        )
    )
}