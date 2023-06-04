package academy.bangkit.jetvive.model.user

object FakeUserDataSource {
    val dummyUsers = listOf(
        User(
            "user-1",
            "John Doe",
            "johndoe",
            "johndoe@example.com",
            "supersecretpassword",
            "+62 812-3456-7890",
            "Indonesian Street No. 45",
            "Male"
        )
    )
}