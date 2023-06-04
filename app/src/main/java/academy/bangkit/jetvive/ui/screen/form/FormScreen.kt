package academy.bangkit.jetvive.ui.screen.form

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.ui.components.DropDownMenu
import academy.bangkit.jetvive.ui.screen.login.LoginViewModel
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FormScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    FormContent(
        navigateToHome = navigateToHome
    )
}

@Composable
fun FormContent(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) { 
        TopSection()
        FormForm(
            navigateToHome = navigateToHome
        )
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.form),
        contentDescription = stringResource(R.string.survey_image),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 60.dp,
                bottom = 30.dp
            )
    )
}

@Composable
fun FormForm(
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(30.dp)
            .fillMaxHeight(.9f)
    ) {
        Text(
            text = stringResource(R.string.tell_us_how_s_your_mood_and_your_preferences),
            fontSize = 26.sp,
            lineHeight = 35.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Divider()
        DropDownMenu(
            label = stringResource(R.string.how_s_your_current_mood),
            listItems = arrayOf("Happy", "Sad", "Calm", "Angry"),
        )
        DropDownMenu(
            label = stringResource(R.string.how_s_your_current_budget),
            listItems = arrayOf("Low", "Medium", "High", "Surprise me!"),
        )
        DropDownMenu(
            label = stringResource(R.string.how_far_are_you_willing_to_travel),
            listItems = arrayOf("Low", "Medium", "High", "Surprise me!")
        )
        DropDownMenu(
            label = stringResource(R.string.what_is_your_preferred_city),
            listItems = arrayOf("Semarang", "Jogja", "Bandung", "Jakarta")
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(
                onClick = { navigateToHome() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.continue_text),
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FormContentPreview() {
    JetViVeTheme {
        FormContent(
            navigateToHome = {}
        )
    }
}