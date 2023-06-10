package academy.bangkit.jetvive.ui.screen.register

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.data.source.remote.request.RegisterRequest
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.helper.isPasswordMatching
import academy.bangkit.jetvive.helper.isPasswordValid
import academy.bangkit.jetvive.helper.isValidEmail
import academy.bangkit.jetvive.ui.common.UiState
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterForm(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: RegisterViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    )
    val context = LocalContext.current
    val registrationStatus by viewModel.registrationStatus.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    if (isLoading) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

    LaunchedEffect(registrationStatus) {
        if (registrationStatus is UiState.Success) {
            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
            navigateToSignIn()
        } else if (registrationStatus is UiState.Error) {
            Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
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

        var name by remember { mutableStateOf(TextFieldValue("")) }
        
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

        var email by remember { mutableStateOf(TextFieldValue("")) }
        var isEmailValid by remember { mutableStateOf(true) }

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
            modifier = Modifier
                .fillMaxWidth(),
            isError = !isEmailValid,
            supportingText = {
                if (!isEmailValid) {
                    Text(
                        text = "Invalid email address",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        var password by remember { mutableStateOf(TextFieldValue("")) }
        var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
        var showPassword by remember { mutableStateOf(value = false) }
        var isPasswordMatching by remember { mutableStateOf(true) }
        var isPasswordValid by remember { mutableStateOf(true) }

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
            modifier = Modifier
                .fillMaxWidth(),
            supportingText = {
                if (!isPasswordValid) {
                    Text(
                        text = "Password must contain at least 8 characters long, one uppercase letter, one lowercase letter, and one digit.",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        )

        var showConfirmPassword by remember { mutableStateOf(value = false) }

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
            isError = !isPasswordMatching,
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            supportingText = {
                if (!isPasswordMatching) {
                    Text(
                        text = "Password did not match",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
        )
        val isAnyFieldEmpty = name.text.isEmpty() || email.text.isEmpty() ||
                password.text.isEmpty() || confirmPassword.text.isEmpty()

        Button(
            onClick = {
                isLoading = true
                viewModel.register(RegisterRequest(
                    name = name.text,
                    email = email.text,
                    password = password.text)
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = !isLoading && !isAnyFieldEmpty
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
            modifier = Modifier.padding(end = 3.dp)
        )
        Text(
            text = stringResource(R.string.sign_in),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
            letterSpacing = .25.sp,
            style = TextStyle(color = Color(0xFF576CBC)),
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