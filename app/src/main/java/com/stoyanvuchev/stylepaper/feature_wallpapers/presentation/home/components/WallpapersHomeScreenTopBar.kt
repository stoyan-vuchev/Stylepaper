package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.core.ui.components.topbar.SmallTopBar
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.Screen
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenUIAction

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WallpapersHomeScreenTopBar(
    modifier: Modifier = Modifier,
    isFirstItemVisible: Boolean,
    isScrollInProgress: Boolean,
    selection: WallpapersHomeSelection,
    onAction: (WallpapersHomeScreenUIAction) -> Unit
) {

    val title = selection.contentDescription.asString()
    val searchButtonDescription = selection.contentDescription.asString()
    val tint = animateColorAsState(
        targetValue = MaterialTheme.colorScheme.onSurfaceVariant
    )

    val pivotX = when (selection) {
        WallpapersHomeSelection.Latest -> 0.25f
        WallpapersHomeSelection.Popular -> 0.75f
        WallpapersHomeSelection.Random -> 1f
    }

    SmallTopBar(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        content = {

            Spacer(modifier = Modifier.width(24.dp))

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {

                if (isScrollInProgress) {

                    androidx.compose.animation.AnimatedVisibility(
                        visible = isFirstItemVisible,
                        enter = fadeIn(animationSpec = tween(durationMillis = 500))
                                + slideInVertically { -it / 2 },
                        exit = fadeOut(
                            animationSpec = tween(durationMillis = 200)
                        ) + slideOutVertically { -it / 2 }
                    ) {
                        Icon(
                            modifier = Modifier.height(22.dp),
                            painter = painterResource(id = R.drawable.logo_with_text),
                            contentDescription = stringResource(id = R.string.app_name),
                            tint = tint.value
                        )
                    }

                    androidx.compose.animation.AnimatedVisibility(
                        visible = !isFirstItemVisible,
                        enter = fadeIn(
                            animationSpec = tween(durationMillis = 250)
                        ) + slideInVertically(
                            animationSpec = tween(durationMillis = 250),
                            initialOffsetY = { (it + (it / 2)) / 2 }
                        ) + scaleIn(
                            animationSpec = tween(durationMillis = 250),
                            transformOrigin = TransformOrigin(
                                pivotFractionX = pivotX,
                                pivotFractionY = 0.5f
                            ),
                            initialScale = 0.75f
                        ),
                        exit = fadeOut(
                            animationSpec = tween(durationMillis = 250)
                        ) + slideOutVertically(
                            animationSpec = tween(durationMillis = 250),
                            targetOffsetY = { (it + (it / 2)) / 2 }
                        ) + scaleOut(
                            animationSpec = tween(durationMillis = 500),
                            transformOrigin = TransformOrigin(
                                pivotFractionX = pivotX,
                                pivotFractionY = 0.5f
                            ),
                            targetScale = 0.75f
                        )
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily(Font(R.font.nunito_semibold))
                            ),
                            color = tint.value
                        )
                    }

                } else {

                    if (isFirstItemVisible) {

                        Icon(
                            modifier = Modifier.height(22.dp),
                            painter = painterResource(id = R.drawable.logo_with_text),
                            contentDescription = stringResource(id = R.string.app_name),
                            tint = tint.value
                        )

                    } else {

                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily(Font(R.font.nunito_semibold))
                            ),
                            color = tint.value
                        )

                    }

                }

            }

            IconButton(
                modifier = Modifier
                    .size(32.dp)
                    .semantics {
                        this.contentDescription = searchButtonDescription
                        this.role = Role.Button
                    },
                onClick = { onAction(WallpapersHomeScreenUIAction.Search) },
                colors = IconButtonDefaults.iconButtonColors(contentColor = tint.value)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = stringResource(id = Screen.Search.description)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

        },
        elevatedColor = Color.Transparent
    )

}