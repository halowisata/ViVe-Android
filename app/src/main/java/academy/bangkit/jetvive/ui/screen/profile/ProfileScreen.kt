package academy.bangkit.jetvive.ui.screen.profile

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.data.source.remote.request.LogoutRequest
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.ui.common.UiState
import academy.bangkit.jetvive.ui.components.Alert
import academy.bangkit.jetvive.ui.components.LoadingDialog
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    navigateToSignIn: () -> Unit,
    launchSnackbar: () -> Unit,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    )
) {
    viewModel.getLogin()

    val uiLoginState by viewModel.uiLoginState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    if (isLoading) {
        LoadingDialog()
    }

    viewModel.uiUserState.collectAsState().value.let { uiUserState ->
        when (uiUserState) {
            is UiState.Loading -> {
                isLoading = true
                viewModel.getUser(accessToken = uiLoginState?.accessToken.toString())
            }
            is UiState.Success -> {
                isLoading = false
                ProfileContent(
                    userImage = R.drawable.jetpack_compose,
                    userName = uiUserState.data.data.name,
                    username = uiUserState.data.data.username,
                    userEmail = uiUserState.data.data.email,
                    userPhoneNumber = uiUserState.data.data.phoneNumber,
                    userAddress = uiUserState.data.data.address,
                    userAge = uiUserState.data.data.age.toString(),
                    userGender = uiUserState.data.data.gender,
                    onBackClick = onBackClick,
                    navigateToSignIn = navigateToSignIn,
                    launchSnackbar = launchSnackbar,
                    viewModel = viewModel
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun ProfileContent(
    userImage: Int? = null,
    userName: String? = null,
    username: String? = null,
    userEmail: String? = null,
    userPhoneNumber: String? = null,
    userAddress: String? = null,
    userAge: String? = null,
    userGender: String? = null,
    onBackClick: () -> Unit,
    navigateToSignIn: () -> Unit,
    launchSnackbar: () -> Unit,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopSection(
            userImage = userImage,
            userName = userName,
            onBackClick = onBackClick,
            navigateToSignIn = navigateToSignIn,
            viewModel = viewModel
        )
        Divider()
        MainSection(
            username = username,
            userEmail = userEmail,
            userPhoneNumber = userPhoneNumber,
            userAddress = userAddress,
            userAge = userAge,
            userGender = userGender,
            launchSnackbar = launchSnackbar
        )
        BottomSection()
    }
}

@Composable
fun TopSection(
    userImage: Int? = null,
    userName: String? = null,
    onBackClick: () -> Unit,
    navigateToSignIn: () -> Unit,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    viewModel.getLogin()

    val uiLoginState by viewModel.uiLoginState.collectAsState()
    val uiLogoutState by viewModel.uiLogoutState.collectAsState()

    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }

    if (isLoading) {
        LoadingDialog()
    }

    LaunchedEffect(uiLogoutState) {
        if (uiLogoutState is UiState.Success) {
            Toast.makeText(context, R.string.logout_successful, Toast.LENGTH_SHORT).show()
            navigateToSignIn()
        } else if (uiLogoutState is UiState.Error) {
            Toast.makeText(context, R.string.login_failed, Toast.LENGTH_SHORT).show()
            isLoading = false
        }
    }

    if (showDialog.value) {
        Alert(title = stringResource(
            R.string.logout),
            name = stringResource(R.string.alert_name),
            showDialog = showDialog.value,
            onConfirm = {
                showDialog.value = false
                isLoading = true
                viewModel.logout(LogoutRequest(
                    refreshToken = uiLoginState?.refreshToken.toString()
                ))
            },
            onDismiss = { showDialog.value = false }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(bottom = 20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .size(25.dp)
                )
            }
            IconButton(
                onClick = {
                    showDialog.value = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = stringResource(R.string.logout),
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
        Image(
            painter = painterResource(userImage ?: 0),
            contentDescription = stringResource(R.string.user_image),
            modifier = Modifier
                .size(150.dp)
        )
        Text(
            text = userName ?: stringResource(R.string.name_not_set),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun MainSection(
    username: String? = null,
    userEmail: String? = null,
    userPhoneNumber: String? = null,
    userAddress: String? = null,
    userAge: String? = null,
    userGender: String? = null,
    launchSnackbar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.user_information),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold

            )
            IconButton(
                onClick = { launchSnackbar() }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit),
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = stringResource(R.string.username_icon)
                )
                Text(
                    text = username ?: stringResource(R.string.username_not_set)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = stringResource(R.string.email_icon)
                )
                Text(
                    text = userEmail ?: stringResource(R.string.email_not_set)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = stringResource(R.string.phone_number_icon)
                )
                Text(
                    text = userPhoneNumber ?: stringResource(R.string.phone_number_not_set)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = stringResource(R.string.address_icon)
                )
                Text(
                    text = userAddress ?: stringResource(R.string.address_not_set)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Numbers,
                    contentDescription = stringResource(R.string.age_icon)
                )
                Text(
                    text = userAge ?: stringResource(R.string.age_not_set)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Male,
                    contentDescription = stringResource(R.string.gender_icon)
                )
                Text(
                    text = userGender ?: stringResource(R.string.gender_not_set)
                )
            }
        }
    }
}

@Composable
fun BottomSection(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.logo_2),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileContentPreview() {
    JetViVeTheme {
        ProfileContent(
            userImage = R.drawable.jetpack_compose,
            userName = stringResource(R.string.user_name),
            username = stringResource(R.string.username),
            userEmail = stringResource(R.string.user_email),
            userPhoneNumber = stringResource(R.string.user_phone_number),
            userAddress = stringResource(R.string.user_address),
            userAge = stringResource(R.string.age_not_set),
            userGender = stringResource(R.string.user_gender),
            onBackClick = {},
            navigateToSignIn = {},
            launchSnackbar = {}
        )
    }
}