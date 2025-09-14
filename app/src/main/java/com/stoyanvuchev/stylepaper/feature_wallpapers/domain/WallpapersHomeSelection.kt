package com.stoyanvuchev.stylepaper.feature_wallpapers.domain

import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.core.etc.UIString

enum class WallpapersHomeSelection {

    Latest {

        override val label: UIString
            get() = UIString.Resource(R.string.latest_wallpapers_selection)

        override val contentDescription: UIString
            get() = UIString.Resource(R.string.latest_wallpapers)

    },

    Popular {

        override val label: UIString
            get() = UIString.Resource(R.string.popular_wallpapers_selection)

        override val contentDescription: UIString
            get() = UIString.Resource(R.string.popular_wallpapers)

    },

    Random {

        override val label: UIString
            get() = UIString.Resource(R.string.random_wallpapers_selection)

        override val contentDescription: UIString
            get() = UIString.Resource(R.string.random_wallpapers)

    };

    companion object {
        val homeScreenSelections by lazy { listOf(Latest, Popular, Random) }
    }

    abstract val label: UIString
    abstract val contentDescription: UIString

}