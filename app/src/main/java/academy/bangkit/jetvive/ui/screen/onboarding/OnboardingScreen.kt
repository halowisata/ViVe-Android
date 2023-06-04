package academy.bangkit.jetvive.ui.screen.onboarding

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.model.onboarding.FakeOnboardingDataSource
import academy.bangkit.jetvive.model.onboarding.Onboarding
import academy.bangkit.jetvive.ui.common.UiState
import academy.bangkit.jetvive.ui.components.OnBoardingItem
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    navigateToLogin: () -> Unit,
    viewModel: OnboardingViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllOnboardingData()
            }
            is UiState.Success -> {
                OnboardingContent(
                    onboardings = uiState.data,
                    navigateToLogin = navigateToLogin
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingContent(
    onboardings: List<Onboarding>,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopSection(
            pageState = pageState.currentPage,
            pageSize = onboardings.size,
            onBackClick = {
                if (pageState.currentPage + 1 > 1) scope.launch {
                    pageState.animateScrollToPage(pageState.currentPage - 1)
                }
            },
            onSkipClick = {
                if (pageState.currentPage + 1 < onboardings.size) scope.launch {
                    pageState.animateScrollToPage(onboardings.size - 1)
                }
            }
        )
        HorizontalPager(
            state = pageState,
            count = onboardings.size,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f)
        ) { page ->
            OnBoardingItem(
                image = onboardings[page].image,
                headline = onboardings[page].headline,
                body = onboardings[page].body
            )
        }
        BottomSection(
            pageState = pageState.currentPage,
            pageSize = onboardings.size,
            index = pageState.currentPage,
            onButtonClick = {
                if (pageState.currentPage + 1 < onboardings.size) scope.launch {
                    pageState.animateScrollToPage(pageState.currentPage + 1)
                } else {
                    navigateToLogin()
                }
            }
        )
    }
}

@Composable
fun TopSection(
    pageState: Int,
    pageSize: Int,
    onBackClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        if (pageState != 0) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
        if (pageState != pageSize - 1) {
            TextButton(
                onClick = onSkipClick,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun BottomSection(
    pageState: Int,
    pageSize: Int,
    index: Int,
    onButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Indicators(
            pageSize,
            index
        )
        FloatingActionButton(
            onClick = onButtonClick,
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(100.dp))
        ) {
            if (pageState == pageSize - 1) {
                Text(
                    text = stringResource(R.string.get_started),
                    style = TextStyle(
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp,
                            vertical = 10.dp
                        )
                )
            } else {
                Text(
                    text = stringResource(R.string.continue_text),
                    style = TextStyle(
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp,
                            vertical = 10.dp
                        )
                )
            }
        }
    }
}

@Composable
fun BoxScope.Indicators(
    size: Int,
    index: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(
                isSelected = it == index
            )
        }
    }
}

@Composable
fun Indicator(
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color =
                if (isSelected) MaterialTheme.colorScheme.primary else Color(0xFFD9D9D9)
            )
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingContentPreview() {
    JetViVeTheme {
        OnboardingContent(
            FakeOnboardingDataSource.dummyOnboardings,
            navigateToLogin = {}
        )
    }
}