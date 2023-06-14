package academy.bangkit.jetvive.ui.screen.survey

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.data.source.remote.request.SurveyRequest
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.ui.common.UiState
import academy.bangkit.jetvive.ui.components.LoadingDialog
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    SurveyContent(
        navigateToHome = navigateToHome
    )
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
            .verticalScroll(rememberScrollState())
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
        painter = painterResource(R.drawable.survey),
        contentDescription = stringResource(R.string.survey_image),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 60.dp,
                bottom = 30.dp
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormForm(
    navigateToHome: () -> Unit,
    viewModel: SurveyViewModel = viewModel(
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
        viewModel.getLogin()

        val uiLoginState by viewModel.uiLoginState.collectAsState()
        val uiSurveyState by viewModel.uiSurveyState.collectAsState()

        var isExpandedMood by remember { mutableStateOf(false) }
        var selectedMoodItem by remember { mutableStateOf("") }
        var isExpandedBudget by remember { mutableStateOf(false) }
        var selectedBudgetItem by remember { mutableStateOf("") }
        var isExpandedFar by remember { mutableStateOf(false) }
        var selectedFarItem by remember { mutableStateOf("") }
        var isExpandedCity by remember { mutableStateOf(false) }
        var selectedCityItem by remember { mutableStateOf("") }

        val listMoodItems = arrayOf(
            stringResource(R.string.happy),
            stringResource(R.string.sad),
            stringResource(R.string.calm),
            stringResource(R.string.angry)
        )
        val listItems = arrayOf(
            stringResource(R.string.low),
            stringResource(R.string.medium),
            stringResource(R.string.high),
            stringResource(R.string.surprise_me
        ))
        val listFarItems = arrayOf(
            stringResource(R.string.low),
            stringResource(R.string.medium),
            stringResource(R.string.high),
            stringResource(R.string.surprise_me
        ))
        val listCityItems = arrayOf(
            "Jakarta",
            "Yogyakarta",
            "Bandung",
            "Semarang",
            "Surabaya"
        )

        val isAnyFieldEmpty = selectedMoodItem.isEmpty()
                || selectedBudgetItem.isEmpty()
                || selectedFarItem.isEmpty()
                || selectedCityItem.isEmpty()

        var isLoading by remember { mutableStateOf(false) }

        val context = LocalContext.current

        if (isLoading) {
            LoadingDialog()
        }

        LaunchedEffect(uiSurveyState) {
            if (uiSurveyState is UiState.Success) {
                Toast.makeText(context, R.string.add_survey_successful, Toast.LENGTH_SHORT).show()
                navigateToHome()
            } else if (uiSurveyState is UiState.Error) {
                Toast.makeText(context, R.string.add_survey_failed, Toast.LENGTH_SHORT).show()
                isLoading = false
            }
        }

        Divider()
        ExposedDropdownMenuBox(
            expanded = isExpandedMood,
            onExpandedChange = { isExpandedMood = !isExpandedMood }
        ) {
            OutlinedTextField(
                value = selectedMoodItem,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = stringResource(R.string.how_s_your_current_mood),) },
                placeholder = { Text(text = stringResource(R.string.select_one)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedMood) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            DropdownMenu(
                expanded = isExpandedMood,
                onDismissRequest = { isExpandedMood = false },
                modifier = Modifier
                    .exposedDropdownSize()
            ) {
                listMoodItems.forEach { selectedOption ->
                    DropdownMenuItem(
                        text =  { Text(text = selectedOption) },
                        onClick = {
                            selectedMoodItem = selectedOption
                            isExpandedMood = false
                        }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = isExpandedBudget,
            onExpandedChange = { isExpandedBudget = !isExpandedBudget }
        ) {
            OutlinedTextField(
                value = selectedBudgetItem,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = stringResource(R.string.how_s_your_current_budget)) },
                placeholder = { Text(text = stringResource(R.string.select_one)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedBudget) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            DropdownMenu(
                expanded = isExpandedBudget,
                onDismissRequest = { isExpandedBudget = false },
                modifier = Modifier
                    .exposedDropdownSize()
            ) {
                listItems.forEach { selectedOption ->
                    DropdownMenuItem(
                        text =  { Text(text = selectedOption) },
                        onClick = {
                            selectedBudgetItem = selectedOption
                            isExpandedBudget = false
                        }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = isExpandedFar,
            onExpandedChange = { isExpandedFar = !isExpandedFar }
        ) {
            OutlinedTextField(
                value = selectedFarItem,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = stringResource(R.string.how_far_are_you_willing_to_travel)) },
                placeholder = { Text(text = stringResource(R.string.select_one)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedFar) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            DropdownMenu(
                expanded = isExpandedFar,
                onDismissRequest = { isExpandedFar = false },
                modifier = Modifier
                    .exposedDropdownSize()
            ) {
                listFarItems.forEach { selectedOption ->
                    DropdownMenuItem(
                        text =  { Text(text = selectedOption) },
                        onClick = {
                            selectedFarItem = selectedOption
                            isExpandedFar = false
                        }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = isExpandedCity,
            onExpandedChange = { isExpandedCity = !isExpandedCity }
        ) {
            OutlinedTextField(
                value = selectedCityItem,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = stringResource(R.string.what_is_your_preferred_city)) },
                placeholder = { Text(text = stringResource(R.string.select_one)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedCity) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            DropdownMenu(
                expanded = isExpandedCity,
                onDismissRequest = { isExpandedCity = false },
                modifier = Modifier
                    .exposedDropdownSize()
            ) {
                listCityItems.forEach { selectedOption ->
                    DropdownMenuItem(
                        text =  { Text(text = selectedOption) },
                        onClick = {
                            selectedCityItem = selectedOption
                            isExpandedCity = false
                        }
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(
                onClick = {
                    isLoading = true
                    viewModel.addSurvey(
                        uiLoginState?.accessToken.toString(),
                        SurveyRequest(
                            mood = selectedMoodItem,
                            budget = selectedBudgetItem,
                            travelDistance = selectedFarItem,
                            destinationCity = selectedCityItem
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                enabled = !isAnyFieldEmpty,
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