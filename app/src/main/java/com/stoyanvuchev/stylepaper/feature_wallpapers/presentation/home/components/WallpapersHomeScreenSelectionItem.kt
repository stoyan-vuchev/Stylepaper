package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenUIAction

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WallpapersHomeScreenSelectionItem(
    modifier: Modifier = Modifier,
    currentSelection: WallpapersHomeSelection,
    isFirstItemVisible: Boolean,
    onAction: (WallpapersHomeScreenUIAction) -> Unit
) {

    val homeScreenSelections = WallpapersHomeSelection.homeScreenSelections

    Box(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .then(modifier)
            .animateContentSize()
    ) {

        AnimatedVisibility(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(),
            visible = isFirstItemVisible,
            enter = fadeIn() + slideInVertically { -it / 2 } + scaleIn(
                initialScale = 0.8f, transformOrigin = TransformOrigin(
                    pivotFractionX = 0.25f,
                    pivotFractionY = 0.5f
                )
            ),
            exit = fadeOut() + slideOutVertically { 0 } + scaleOut(
                targetScale = 0.8f, transformOrigin = TransformOrigin(
                    pivotFractionX = 0.25f,
                    pivotFractionY = 0.5f
                )
            ),
        ) {

            LazyRow(
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                item { Spacer(modifier = Modifier.width(2.dp)) }

                items(homeScreenSelections, key = { it.label }) { selection ->

                    val selected = selection == currentSelection

                    val contentDescription = selection.contentDescription.asString()
                    val label = selection.label.asString()

                    val bgColor = animateColorAsState(
                        targetValue = MaterialTheme.colorScheme.primaryContainer.copy(
                            alpha = if (selected) 1f else 0f
                        )
                    )

                    val textColor = animateColorAsState(
                        targetValue = if (selected) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.inverseSurface
                        }
                    )

                    Button(
                        modifier = Modifier.semantics {
                            this.contentDescription = contentDescription
                            this.role = Role.Button
                        },
                        onClick = {
                            if (!selected) onAction(WallpapersHomeScreenUIAction.Selection(selection))
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = bgColor.value,
                            contentColor = textColor.value
                        )
                    ) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = FontFamily(Font(R.font.nunito_semibold))
                            )
                        )
                    }

                }

                item { Spacer(modifier = Modifier.width(2.dp)) }

            }

        }

    }

}