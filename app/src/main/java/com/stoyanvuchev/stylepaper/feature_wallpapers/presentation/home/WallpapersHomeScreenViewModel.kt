package com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.core.etc.UIString
import com.stoyanvuchev.stylepaper.core.ext.restoreState
import com.stoyanvuchev.stylepaper.core.ui.event.NavigationEvent
import com.stoyanvuchev.stylepaper.core.ui.event.SnackbarEvent
import com.stoyanvuchev.stylepaper.core.ui.paging.DefaultPaginator
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.DispatcherProvider
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.WallpapersHomeSelection
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.model.WallpapersListingModel
import com.stoyanvuchev.stylepaper.feature_wallpapers.domain.repository.WallpapersRepository
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.Screen
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenUIAction.LoadNextItems
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenUIAction.ScrollToTop
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenUIAction.Search
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenUIAction.Selection
import com.stoyanvuchev.stylepaper.feature_wallpapers.presentation.home.WallpapersHomeScreenUIAction.ViewWallpaper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WallpapersHomeScreenViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val repository: WallpapersRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /** The total number of allowed pages */

    private val maxPagesCount = 15

    /** State */

    private val _state = MutableStateFlow(WallpapersHomeScreenState())
    val state = _state.asStateFlow()

    private fun restoreState() {
        _state.update {
            savedStateHandle.restoreState(
                key = "wallpapersHomeScreenState",
                initialState = WallpapersHomeScreenState()
            )
        }
    }

    /** Action Event */

    private val _actionEvent = Channel<WallpapersHomeScreenUIAction>()
    val actionEvent = _actionEvent.receiveAsFlow()

    fun onUIAction(action: WallpapersHomeScreenUIAction) {
        when (action) {
            is Search -> sendNavEvent(NavigationEvent.Navigate(Screen.Search))
            is ScrollToTop -> sendActionEventToUI(action = action)
            is LoadNextItems -> loadNextPaginatorItems()
            is Selection -> handleSelectionChange(action.selection)
            is ViewWallpaper -> {
                /*sendNavEvent(
                    NavigationEvent.Navigate(
                        Screen.WallpaperDetails(wallpaperId = action.wallpaperId)
                    )
                )*/
                showSnackbar(UIString.Resource(R.string.not_implemented))
            }
        }
    }

    private fun sendActionEventToUI(action: WallpapersHomeScreenUIAction) {
        viewModelScope.launch(dispatchers.main) {
            _actionEvent.send(action)
        }
    }

    private fun handleSelectionChange(selection: WallpapersHomeSelection) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    selection = selection,
                    endReached = false
                )
            }
            sendActionEventToUI(Selection(selection))
            delay(100L)
            resetPaginator()
            delay(100L)
            _state.update {
                it.copy(
                    wallpapersListing = it.wallpapersListing.copy(
                        wallpapers = mutableListOf()
                    )
                )
            }
            loadNextPaginatorItems()
        }
    }

    /** Navigation Event */

    private val _navigationEvent = Channel<NavigationEvent>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    private fun sendNavEvent(event: NavigationEvent) {
        viewModelScope.launch(dispatchers.main) {
            _navigationEvent.send(event)
        }
    }

    /** Snackbar Event */

    private val _snackbarEvent = Channel<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    fun showSnackbar(msg: UIString) {
        viewModelScope.launch(dispatchers.main) {
            _snackbarEvent.send(SnackbarEvent.Snackbar(msg = msg))
        }
    }

    /** Paginator */

    private val paginator = DefaultPaginator(
        initialKey = _state.value.wallpapersListing.currentPage,
        onLoadChanged = {
            _state.update { currentState ->
                currentState.copy(isLoading = it)
            }
        },
        onRequest = { nextPage ->
            withContext(dispatchers.io) {
                repository.loadHomeWallpapersListing(
                    _state.value.selection,
                    nextPage,
                    _state.value.wallpapersListing.seed
                )
            }
        },
        getNextKey = { it.data!!.currentPage + 1 },
        onError = {
            val errorMsg = it ?: UIString.Resource(R.string.something_went_wrong)
            _state.update { currentState -> currentState.copy(error = errorMsg) }
            showSnackbar(errorMsg)
        },
        onSuccess = { wallpapersListing, _ ->
            _state.update {
                it.copy(
                    wallpapersListing = wallpapersListing.copy(
                        wallpapers = (
                                it.wallpapersListing.wallpapers
                                        + wallpapersListing.wallpapers
                                ).toMutableList()
                    ),
                    endReached = wallpapersListing.currentPage == maxPagesCount
                )
            }
        }
    )

    private fun loadNextPaginatorItems() = viewModelScope.launch(dispatchers.main) {
        withContext(dispatchers.io) {
            _state.update { it.copy(error = null) }
            paginator.loadNextItems()
        }
    }

    private fun resetPaginator() = viewModelScope.launch(dispatchers.main) {
        withContext(dispatchers.io) {
            paginator.reset()
            _state.update { it.copy(wallpapersListing = WallpapersListingModel()) }
        }
    }

    /** Do stuff when initializing the ViewModel */

    init {
        viewModelScope.launch {
            delay(200L)
            restoreState()
            loadNextPaginatorItems()
        }
    }

}