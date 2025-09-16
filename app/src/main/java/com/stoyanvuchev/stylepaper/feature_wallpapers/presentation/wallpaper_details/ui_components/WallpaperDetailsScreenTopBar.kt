package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.ui_components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenUIAction

@Composable
fun WallpaperDetailsScreenTopBar(
    modifier: Modifier = Modifier,
    isFirstItemVisible: Boolean,
    onActionEvent: (WallpaperDetailsScreenUIAction) -> Unit
) {

    val title = stringResource(id = R.string.wallpaper_details_screen_label)
    val backButtonDescription = stringResource(id = R.string.back)
    val saveButtonDescription = stringResource(id = R.string.save_wallpaper)

    val tint = MaterialTheme.colorScheme.onSurfaceVariant
    val elevatedColor = animateColorAsState(
        targetValue = MaterialTheme.colorScheme.primary.copy(
            if (isFirstItemVisible) 0f else 0.08f
        )
    )

    SmallTopBar(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        content = {

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(
                modifier = Modifier
                    .size(32.dp)
                    .semantics {
                        this.contentDescription = backButtonDescription
                        this.role = Role.Button
                    },
                onClick = { onActionEvent(WallpaperDetailsScreenUIAction.Back) },
                colors = IconButtonDefaults.iconButtonColors(contentColor = tint)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.arrow_left_icon),
                    contentDescription = backButtonDescription
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily(Font(R.font.nunito_semibold))
                ),
                color = tint
            )

            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .width(16.dp)
            )

            IconButton(
                modifier = Modifier
                    .size(32.dp)
                    .semantics {
                        this.contentDescription = saveButtonDescription
                        this.role = Role.Button
                    },
                onClick = { onActionEvent(WallpaperDetailsScreenUIAction.Save) },
                colors = IconButtonDefaults.iconButtonColors(contentColor = tint)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.favorite_outlined),
                    contentDescription = saveButtonDescription
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

        },
        elevatedColor = elevatedColor.value
    )

}