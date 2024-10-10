package dev.nashe.pokemon.util

import dev.nashe.pokemon.domain.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class CoroutineTestRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher(),
    val scope: TestScope = TestScope(dispatcher),
) : TestWatcher() {

    val testDispatcherProvider = object : DispatcherProvider {
        override fun default(): CoroutineDispatcher = dispatcher

        override fun io(): CoroutineDispatcher = dispatcher

        override fun main(): CoroutineDispatcher = dispatcher

        override fun unconfined(): CoroutineDispatcher = dispatcher
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }

    fun runTestScope(unitTest: suspend TestScope.() -> Unit) {
        scope.runTest {
            unitTest()
        }
    }
}
