package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.ui_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.core.ui.tags.TagItem
import com.stoyanvuchev.stylepaper.core.ui.tags.TagLayout
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpaperTagModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WallpaperDetailsScreenTagsItem(
    modifier: Modifier = Modifier,
    tags: List<WallpaperTagModel>
) {

    AnimatedVisibility(
        modifier = modifier,
        visible = tags.isNotEmpty(),
        enter = fadeIn(
            animationSpec = tween(durationMillis = 768)
        ) + slideInVertically { it * 2 },
        exit = ExitTransition.None
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.tags),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(16.dp))

            TagLayout {

                tags.forEach { tag ->

                    TagItem(label = tag.name)

                }

            }

        }

    }

}