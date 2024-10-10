package dev.nashe.pokemon.domain.usecases.base

import dev.nashe.pokemon.domain.util.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import dev.nashe.pokemon.domain.util.Result

interface UseCase<in P, out R> {

    /**
     * The dispatcher to be used for the operations.
     */
    val dispatcher: DispatcherProvider

    /**
     * The start function expected to be called by creators of the [UseCase]
     *
     * It provides the base setup by catching all exceptions and defining the right dispatcher for all operations to run
     *
     * @param parameters: The input params
     * @return a [Flow] of the [Result] of the output type [R]
     */
    suspend fun start(parameters: P): Flow<Result<R>> {
        return execute(parameters)
            .catch { e -> emit(Result.Error(Exception(e))) }
            .flowOn(dispatcher.io())
    }

    /**
     * The execute function expected to be implemented by the concrete class of the UseCase.
     *
     * @param params: The input params
     * @return a [Flow] of the [Result] of the output type [R]
     */
    fun execute(parameters: P): Flow<Result<R>>
}
