package com.stoyanvuchev.stylepaper.core.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.R
import kotlinx.coroutines.delay

@Composable
fun SnackbarHostAndScrollButton(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    isScrollToTopButtonVisible: Boolean,
    isScrollInProgress: Boolean,
    firstVisibleItem: Int,
    onScrollStateChange: (Boolean) -> Unit,
    onScrollToTop: () -> Unit
) {

    LaunchedEffect(isScrollInProgress) {
        if (firstVisibleItem >= 2) {
            onScrollStateChange(true)
            delay(3000L)
            onScrollStateChange(false)
        } else {
            onScrollStateChange(false)
        }
    }

    Column(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val scrollToTopButtonDescription = stringResource(id = R.string.scroll_to_top)

        ScrollButton(
            modifier = Modifier
                .navigationBarsPadding()
                .semantics {
                    this.contentDescription = scrollToTopButtonDescription
                    this.role = Role.Button
                },
            isVisible = isScrollToTopButtonVisible,
            painter = painterResource(id = R.drawable.arrow_up_icon),
            onClick = { if (isScrollToTopButtonVisible) onScrollToTop() }
        )

        SnackbarHost(
            modifier = Modifier
                .padding(top = 16.dp)
                .animateContentSize(),
            hostState = snackbarHostState
        )

    }

}