package com.stoyanvuchev.stylepaper.core.etc

sealed class Result<T>(
    val data: T? = null,
    val error: UIString? = null
) {

    class Loading<T> : Result<T>()

    class Success<T>(
        data: T?,
        error: UIString? = null
    ) : Result<T>(data, error)

    class Error<T>(
        data: T? = null,
        error: UIString?
    ) : Result<T>(data, error)

}
