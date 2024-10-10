package dev.nashe.pokemon.domain.util

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val exception: Throwable? = null) : Result<Nothing>()

    object Loading : Result<Nothing>()
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data
