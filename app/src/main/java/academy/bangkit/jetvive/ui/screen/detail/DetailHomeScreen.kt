package academy.bangkit.jetvive.ui.screen.detail

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.data.source.remote.request.SaveTouristAttractionRequest
import academy.bangkit.jetvive.helper.SharedViewModel
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.ui.common.UiState
import academy.bangkit.jetvive.ui.components.LoadingDialog
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailHomeScreen(
    sharedViewModel: SharedViewModel,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    val selectedItem = sharedViewModel.selectedTouristAttractionItem

    DetailHomeContent(
        touristAttractionImage = R.drawable.tourist_attraction_image,
        touristAttractionName = selectedItem!!.name,
        touristAttractionDescription = selectedItem.description,
        touristAttractionCity = selectedItem.city,
        touristAttractionRating = selectedItem.rating,
        touristAttractionLat = selectedItem.lat,
        touristAttractionLon = selectedItem.lon,
        onBackClick = onBackClick
    )
}

@Composable
fun DetailHomeContent(
    @DrawableRes touristAttractionImage: Int,
    touristAttractionName: String,
    touristAttractionDescription: String,
    touristAttractionCity: String,
    touristAttractionRating: Double,
    touristAttractionLat: Double,
    touristAttractionLon: Double,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    modifier: Modifier = Modifier,
) {
    viewModel.getLogin()

    val uiLoginState by viewModel.uiLoginState.collectAsState()
    val uiSaveTouristAttractionState by
        viewModel.uiSaveTouristAttractionState.collectAsState()

    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    if (isLoading) {
        LoadingDialog()
    }

    LaunchedEffect(uiSaveTouristAttractionState) {
        if (uiSaveTouristAttractionState is UiState.Success) {
            Toast.makeText(
                context,
                R.string.tourist_attraction_has_been_saved_to_bookmark,
                Toast.LENGTH_SHORT
            ).show()
        } else if (uiSaveTouristAttractionState is UiState.Error) {
            Toast.makeText(
                context,
                R.string.failed_to_save_tourist_attraction_to_bookmark,
                Toast.LENGTH_SHORT
            ).show()
        }
        isLoading = false
    }

    Column {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(touristAttractionImage),
                    contentDescription = stringResource(R.string.tourist_attraction_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
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
                            isLoading = true
                            viewModel.saveTouristAttraction(
                                uiLoginState?.accessToken.toString(),
                                SaveTouristAttractionRequest(
                                    name = touristAttractionName,
                                    city = touristAttractionCity,
                                    description = touristAttractionDescription,
                                    rating = touristAttractionRating,
                                    lat = touristAttractionLat,
                                    lon = touristAttractionLon
                                )
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = stringResource(R.string.bookmark),
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = touristAttractionName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = stringResource(R.string.location_icon),
                            tint = Color.Red
                        )
                        Text(
                            text = touristAttractionCity,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = stringResource(R.string.star_icon),
                            tint = Color.Yellow
                        )
                        Text(
                            text = touristAttractionRating.toString(),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(LightGray)
                )
                Text(
                    text = touristAttractionDescription,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                )
                Button(
                    onClick = {
                        val uri = Uri.parse(
                            "geo:$touristAttractionLat,$touristAttractionLon"
                        )
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.open_on_google_maps),
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailHomeContentPreview() {
    JetViVeTheme {
        DetailHomeContent(
            touristAttractionImage = R.drawable.jetpack_compose,
            touristAttractionName = stringResource(R.string.tourist_attraction_name),
            touristAttractionDescription = stringResource(R.string.tourist_attraction_description),
            touristAttractionCity = stringResource(R.string.tourist_attraction_city),
            touristAttractionRating = 0.0,
            touristAttractionLat = 0.0,
            touristAttractionLon = 0.0,
            onBackClick = {}
        )
    }
}