package academy.bangkit.jetvive.ui.screen.login

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    LoginContent()
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopSection()
        LoginForm()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = stringResource(R.string.sign_in),
            modifier = Modifier
                .fillMaxWidth()
        )
        var email by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = email,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            onValueChange = {
                email = it
            },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "johndoe@example.com") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
        )
        var password by remember { mutableStateOf(TextFieldValue("")) }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password") },
            placeholder = { Text(text = "********") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            },
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.forget_password),
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},

                ) {
                Text(
                    text = "Login",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(text = "New to ViVe?")
                Text(text = "Sign Up")
            }
        }
    }
}

@Composable
fun TopSection() {
    Image(
        painter = painterResource(R.drawable.jetpack_compose),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .size(300.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    JetViVeTheme {
        LoginContent()
    }
}