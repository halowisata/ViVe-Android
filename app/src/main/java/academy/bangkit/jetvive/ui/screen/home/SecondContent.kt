package academy.bangkit.jetvive.ui.screen.home

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.ui.screen.login.LoginViewModel
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SecondContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) { 
        TopSection()
        SurveyForm()
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.survey),
        contentDescription = null,
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
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxHeight(.9f),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.sign_in),
            fontSize = 26.sp,
            lineHeight = 35.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        var email by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = email,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            onValueChange = {
                email = it
            },
            label = { Text(text = "Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
//        var email by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = email,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            onValueChange = {
                email = it
            },
            label = { Text(text = "Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
//        var email by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = email,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            onValueChange = {
                email = it
            },
            label = { Text(text = "Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            val coroutineScope = rememberCoroutineScope()
            Button(
                onClick = {},
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
fun SecondContentPreview() {
    JetViVeTheme {
        SecondContent()
    }
}