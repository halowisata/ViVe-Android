package academy.bangkit.jetvive.ui.screen.login

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    navigateToSignUp: () -> Unit,
    navigateToForm: () -> Unit,
    launchSnackbar: () -> Unit,
    modifier: Modifier = Modifier
) {
    LoginContent(
        navigateToSignUp = navigateToSignUp,
        navigateToForm = navigateToForm,
        launchSnackbar = launchSnackbar
    )
}

@Composable
fun LoginContent(
    navigateToSignUp: () -> Unit,
    navigateToForm: () -> Unit,
    launchSnackbar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopSection()
        LoginForm(
            navigateToForm = navigateToForm,
            launchSnackbar = launchSnackbar
        )
        BottomSection(
            navigateToSignUp = navigateToSignUp
        )
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.sign_in),
        contentDescription = stringResource(R.string.sign_in_image),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 60.dp,
                bottom = 30.dp
            )
    )
}

@SuppressLint("StringFormatInvalid")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    navigateToForm: () -> Unit,
    launchSnackbar: () -> Unit,
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
            leadingIcon = { Icon(
                imageVector = Icons.Default.Email,
                contentDescription = stringResource(R.string.email)
            ) },
            onValueChange = {
                email = it
            },
            label = { Text(
                text = stringResource(R.string.email)
            ) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        var password by remember { mutableStateOf(TextFieldValue("")) }
        var showPassword by remember { mutableStateOf(value = false) }
        
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = password,
            leadingIcon = { Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = stringResource(R.string.password)
            ) },
            onValueChange = {
                password = it
            },
            label = { Text(
                text = stringResource(R.string.password)
            ) },
            singleLine = true,
            visualTransformation =
                if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                if (showPassword) {
                    IconButton(
                        onClick = { showPassword = false }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = stringResource(R.string.hide_password)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = stringResource(R.string.hide_password)
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.forget_password),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            letterSpacing = .25.sp,
            style = TextStyle(
                color = Color(0xFF576CBC)
            ),
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.End)
                .clickable {
                    launchSnackbar()
                }
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(
                onClick = {
                  navigateToForm()
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.sign_in),
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
            Text(
                text = stringResource(R.string.or),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                border = BorderStroke(
                    1.dp,
                    Color(0xFF79747E)
                ),
                onClick = {
                    launchSnackbar()
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.google_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        text = stringResource(R.string.sign_in_with_google),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun BottomSection(
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.new_to_halowisata),
            fontSize = 14.sp,
            lineHeight = 18.sp,
            letterSpacing = .25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                end = 3.dp
            )
        )
        Text(
            text = stringResource(R.string.sign_up),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
            letterSpacing = .25.sp,
            style = TextStyle(
                color = Color(0xFF576CBC)
            ),
            modifier = Modifier
                .clickable {
                    navigateToSignUp()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    JetViVeTheme {
        LoginContent(
            navigateToSignUp = {},
            navigateToForm = {},
            launchSnackbar = {}
        )
    }
}