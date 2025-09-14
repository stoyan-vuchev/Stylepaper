package com.stoyanvuchev.stylepaper.core.ui.event

import com.stoyanvuchev.stylepaper.core.ui.navhost.NavScreen
import kotlin.reflect.KClass

sealed interface NavigationEvent {
    data object Back : NavigationEvent
    data class Navigate(val route: NavScreen) : NavigationEvent
}