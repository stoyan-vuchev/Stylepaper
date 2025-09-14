package com.stoyanvuchev.stylepaper.core.ui.paging

import com.stoyanvuchev.stylepaper.R
import com.stoyanvuchev.stylepaper.core.etc.Result
import com.stoyanvuchev.stylepaper.core.etc.UIString

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private val onLoadChanged: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> Result<Item>,
    private val getNextKey: suspend (Result<Item>) -> Key,
    private val onError: suspend (UIString?) -> Unit,
    private val onSuccess: suspend (result: Item, newKey: Key) -> Unit,
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {

        if (isMakingRequest) {
            return
        }

        isMakingRequest = true
        onLoadChanged(true)

        val result = onRequest(currentKey)

        if (result is Result.Error) {
            isMakingRequest = false
            onLoadChanged(false)
            onError(result.error ?: UIString.Resource(R.string.something_went_wrong))
            return
        }

        currentKey = getNextKey(result)
        onSuccess(result.data!!, currentKey)
        onLoadChanged(false)
        isMakingRequest = false

    }

    override fun reset() {
        currentKey = initialKey
    }

}

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}