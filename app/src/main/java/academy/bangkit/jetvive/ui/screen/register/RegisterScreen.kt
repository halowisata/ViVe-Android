package academy.bangkit.jetvive.ui.screen.register

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.data.source.remote.request.RegisterRequest
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.helper.isPasswordMatching
import academy.bangkit.jetvive.helper.isPasswordValid
import academy.bangkit.jetvive.helper.isValidEmail
import academy.bangkit.jetvive.ui.common.UiState
import academy.bangkit.jetvive.ui.components.LoadingDialog
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
fun RegisterScreen(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    RegisterContent(
        navigateToSignIn = navigateToSignIn
    )
}

@Composable
fun RegisterContent(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopSection()
        RegisterForm(
            navigateToSignIn = navigateToSignIn,
        )
        BottomSection(
            navigateToSignIn = navigateToSignIn
        )
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.sign_up),
        contentDescription = stringResource(R.string.sign_up_image),
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
fun RegisterForm(
    navigateToSignIn: () -> Unit,
    viewModel: RegisterViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current
        )
    ),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }

    var showPassword by remember { mutableStateOf(value = false) }
    var showConfirmPassword by remember { mutableStateOf(value = false) }

    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isPasswordMatching by remember { mutableStateOf(true) }
    val isAnyFieldEmpty = name.text.isEmpty()
                || email.text.isEmpty()
                || password.text.isEmpty()
                || confirmPassword.text.isEmpty()

    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    if (isLoading) {
        LoadingDialog()
    }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Success) {
            Toast.makeText(context, R.string.registration_successful, Toast.LENGTH_SHORT).show()
            navigateToSignIn()
        } else if (uiState is UiState.Error) {
            Toast.makeText(context, R.string.registration_failed, Toast.LENGTH_SHORT).show()
            isLoading = false
        }
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(30.dp)
            .fillMaxHeight(.9f)
    ) {
        Text(
            text = stringResource(R.string.sign_up),
            fontSize = 26.sp,
            lineHeight = 35.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = name,
            leadingIcon = { Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(R.string.name)
            ) },
            onValueChange = {
                name = it
            },
            label = { Text(
                text = stringResource(R.string.name)
            ) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = email,
            leadingIcon = { Icon(
                imageVector = Icons.Default.Email,
                contentDescription = stringResource(R.string.email)
            ) },
            onValueChange = {
                email = it
                isEmailValid = isValidEmail(it.text)
            },
            label = { Text(
                text = stringResource(R.string.email)
            ) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = !isEmailValid,
            supportingText = {
                if (!isEmailValid) {
                    Text(
                        text = stringResource(R.string.invalid_email_address),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
        )
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = password,
            leadingIcon = { Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = stringResource(R.string.password)
            ) },
            onValueChange = {
                password = it
                isPasswordValid = isPasswordValid(it.text)
            },
            label = { Text(
                text = stringResource(R.string.password)
            ) },
            singleLine = true,
            visualTransformation =
            if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
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
            isError = !isPasswordValid,
            supportingText = {
                if (!isPasswordValid) {
                    Text(
                        text = stringResource(R.string.password_validation),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
        )
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = confirmPassword,
            leadingIcon = { Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = stringResource(R.string.confirm_password)
            ) },
            onValueChange = {
                confirmPassword = it
                isPasswordMatching = isPasswordMatching(password.text, confirmPassword.text)
            },
            label = { Text(
                text = stringResource(R.string.confirm_password)
            ) },
            singleLine = true,
            visualTransformation =
                if (showConfirmPassword) VisualTransformation.None
                else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                if (showConfirmPassword) {
                    IconButton(
                        onClick = { showConfirmPassword = false }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = stringResource(R.string.hide_password)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showConfirmPassword = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = stringResource(R.string.hide_password)
                        )
                    }
                }
            },
            enabled = password.text.isNotEmpty() && isPasswordValid(password.text),
            isError = !isPasswordMatching,
            supportingText = {
                if (!isPasswordMatching) {
                    Text(
                        text = stringResource(R.string.password_does_not_match),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
        )
        Button(
            onClick = {
                isLoading = true
                viewModel.register(RegisterRequest(
                    name = name.text,
                    email = email.text,
                    password = password.text,
                    confirmPassword = confirmPassword.text
                ))
            },
            enabled = !isLoading && !isAnyFieldEmpty && isPasswordMatching,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                style = TextStyle(
                    color = Color.White
                )
            )
        }
    }
}

@Composable
fun BottomSection(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.joined_us_before),
            fontSize = 14.sp,
            lineHeight = 18.sp,
            letterSpacing = .25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(end = 3.dp)
        )
        Text(
            text = stringResource(R.string.sign_in),
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
                    navigateToSignIn()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterContentPreview() {
    JetViVeTheme {
        RegisterContent(
            navigateToSignIn = {},
        )
    }
}