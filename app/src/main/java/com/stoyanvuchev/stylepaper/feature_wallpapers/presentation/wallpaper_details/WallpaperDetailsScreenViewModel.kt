package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.core.etc.Result
import com.stoyanvuchev.stylepaper.core.etc.UIString
import com.stoyanvuchev.stylepaper.core.ext.restoreState
import com.stoyanvuchev.stylepaper.core.ext.saveState
import com.stoyanvuchev.stylepaper.core.ui.event.NavigationEvent
import com.stoyanvuchev.stylepaper.core.ui.event.SnackbarEvent
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.DispatcherProvider
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.repository.WallpapersRepository
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenUIAction.Apply
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenUIAction.Back
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenUIAction.Download
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenUIAction.Fullscreen
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenUIAction.Save
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.wallpaper_details.WallpaperDetailsScreenUIAction.ScrollToTop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WallpaperDetailsScreenViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val repository: WallpapersRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /** Get the ID of the Wallpaper */

    private var _wallpaperId = savedStateHandle.get<String>("wallpaperId") ?: ""

    /** State */

    private val _state = MutableStateFlow(WallpaperDetailsScreenState(wallpaperId = _wallpaperId))
    val state = _state.asStateFlow()

    private fun restoreState() {
        updateState(
            savedStateHandle.restoreState(
                key = "wallpaperDetailsScreenState",
                initialState = WallpaperDetailsScreenState(wallpaperId = _wallpaperId)
            )
        )
    }

    private fun updateState(newState: WallpaperDetailsScreenState) {
        savedStateHandle.saveState(
            key = "wallpaperDetailsScreenState",
            newState = newState
        )
        _state.update { newState }
    }

    /** Action Event */

    private val _actionEvent = Channel<WallpaperDetailsScreenUIAction>()
    val actionEvent = _actionEvent.receiveAsFlow()

    fun onUIAction(action: WallpaperDetailsScreenUIAction) {
        when (action) {
            is Back -> sendNavEvent(NavigationEvent.Back)
            is Fullscreen -> showSnackbar(UIString.Resource(R.string.not_implemented))
            is ScrollToTop -> sendActionEvent(action)
            is Download -> showSnackbar(UIString.Resource(R.string.not_implemented))
            is Apply -> showSnackbar(UIString.Resource(R.string.not_implemented))
            is Save -> showSnackbar(UIString.Resource(R.string.not_implemented))
        }
    }

    private fun sendActionEvent(action: WallpaperDetailsScreenUIAction) {
        viewModelScope.launch {
            _actionEvent.send(action)
        }
    }

    /** Navigation Event */

    private val _navigationEvent = Channel<NavigationEvent>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    private fun sendNavEvent(event: NavigationEvent) {
        viewModelScope.launch(dispatchers.main) { _navigationEvent.send(event) }
    }

    /** Snackbar Event */

    private val _snackbarEvent = Channel<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    fun showSnackbar(msg: UIString) {
        viewModelScope.launch(dispatchers.main) {
            _snackbarEvent.send(SnackbarEvent.Snackbar(msg = msg))
        }
    }

    /** Get the Wallpaper */
    private fun getWallpaper() = viewModelScope.launch(dispatchers.io) {

        when (val result = repository.loadWallpaperById(_state.value.wallpaperId)) {

            is Result.Success -> {

                withContext(dispatchers.main) {
                    if (result.data != null) {
                        updateState(
                            newState = _state.value.copy(
                                wallpaper = result.data,
                                isLoading = false
                            )
                        )
                    }
                }

            }

            is Result.Error -> {

                val errorMsg = result.error
                    ?: UIString.Resource(R.string.something_went_wrong)

                withContext(dispatchers.main) {
                    updateState(_state.value.copy(error = errorMsg, isLoading = false))
                    showSnackbar(errorMsg)
                }

            }

            else -> Unit
        }

    }

    /** Do stuff when initializing the ViewModel */

    init {
        viewModelScope.launch {
            restoreState()
            getWallpaper()
        }
    }

}