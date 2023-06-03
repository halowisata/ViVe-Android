package academy.bangkit.jetvive.ui.screen.survey

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
fun SurveyScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    SurveyContent(navigateToHome)
}

@Composable
fun SurveyContent(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) { 
        TopSection()
        SurveyForm(navigateToHome)
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.survey),
        contentDescription = stringResource(R.string.survey_image),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = 90.dp,
                bottom = 30.dp
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurveyForm(
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
            text = stringResource(R.string.survey),
            fontSize = 26.sp,
            lineHeight = 35.sp,
            modifier = Modifier
                .fillMaxWidth()
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
            val coroutineScope = rememberCoroutineScope()
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
fun SurveyContentPreview() {
    JetViVeTheme {
        SurveyContent(
            navigateToHome = {}
        )
    }
}