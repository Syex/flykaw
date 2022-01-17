package io.github.syex.flykaw

import io.github.syex.flykaw.LogLevel.*
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNull

class LogLevelTest {

    private val logger = TestLogger()

    private val testLogMessage = "HelloLogger"

    @BeforeTest
    fun setup() {
        LogConfig.setLogger(logger)
    }

    @AfterTest
    fun teardown() {
        LogConfig.setLogger(null)
        LogConfig.setLogLevel(VERBOSE)
    }

    @Test
    fun `ignores verbose logs`() {
        LogConfig.setLogLevel(DEBUG)

        logVerbose(testLogMessage)

        assertNull(logger.message)
    }

    @Test
    fun `ignores debug logs`() {
        LogConfig.setLogLevel(INFO)

        logDebug(testLogMessage)

        assertNull(logger.message)
    }

    @Test
    fun `ignores info logs`() {
        LogConfig.setLogLevel(WARNING)

        logInfo(testLogMessage)

        assertNull(logger.message)
    }

    @Test
    fun `ignores warning logs`() {
        LogConfig.setLogLevel(ERROR)

        logWarning(testLogMessage)

        assertNull(logger.message)
    }
}