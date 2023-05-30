package academy.bangkit.jetvive.ui.screen.register

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
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

@Composable
fun RegisterScreen(navigateToSignIn: () -> Unit) {
    RegisterContent(navigateToSignIn)
}

@Composable
fun RegisterContent(navigateToSignIn: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopSection()
        RegisterForm()
        BottomSection(navigateToSignIn)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterForm() {
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxHeight(.9f),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.sign_up),
            fontSize = 26.sp,
            lineHeight = 35.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        var nama by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = nama,
            leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) },
            //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            onValueChange = {
                nama = it
            },
            label = { Text(text = "Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
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
        var password by remember { mutableStateOf(TextFieldValue("")) }
        var showPassword by remember { mutableStateOf(value = false) }
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = password,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password") },
            singleLine = true,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
        var showConfirmPassword by remember { mutableStateOf(value = false) }
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = confirmPassword,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            onValueChange = {
                confirmPassword = it
            },
            label = { Text(text = "Confirm Password") },
            singleLine = true,
            visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                if (showConfirmPassword) {
                    IconButton(onClick = { showConfirmPassword = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showConfirmPassword = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
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
                    navigateToSignUp()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterContentPreview() {
    JetViVeTheme {
        RegisterContent(navigateToSignIn = {})
    }
}