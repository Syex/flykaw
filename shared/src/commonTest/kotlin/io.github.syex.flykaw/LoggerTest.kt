package io.github.syex.flykaw

import kotlin.test.*

class LoggerTest {

    private val logger = TestLogger()
    
    private val testLogMessage = "HelloLogger"

    @BeforeTest
    fun setup() {
        LogConfig.setLogger(logger)
    }

    @AfterTest
    fun teardown() {
        LogConfig.setLogger(null)
    }

    @Test
    fun `delegates verbose logs to a logger`() {
        logVerbose(testLogMessage)

        assertEquals(testLogMessage, logger.message)
        assertEquals("LoggerTest", logger.tag)
        assertEquals(LogLevel.VERBOSE, logger.logLevel)
        assertNull(logger.throwable)
    }

    @Test
    fun `delegates debug logs to a logger`() {
        logDebug(testLogMessage)

        assertEquals(testLogMessage, logger.message)
        assertEquals("LoggerTest", logger.tag)
        assertEquals(LogLevel.DEBUG, logger.logLevel)
        assertNull(logger.throwable)
    }

    @Test
    fun `delegates info logs to a logger`() {
        logInfo(testLogMessage)

        assertEquals(testLogMessage, logger.message)
        assertEquals("LoggerTest", logger.tag)
        assertEquals(LogLevel.INFO, logger.logLevel)
        assertNull(logger.throwable)
    }

    @Test
    fun `delegates warning logs to a logger`() {
        logWarning(testLogMessage)

        assertEquals(testLogMessage, logger.message)
        assertEquals("LoggerTest", logger.tag)
        assertEquals(LogLevel.WARNING, logger.logLevel)
        assertNull(logger.throwable)
    }

    @Test
    fun `delegates error logs to a logger`() {
        val throwable = Throwable()
        logError(testLogMessage, throwable)

        assertEquals(testLogMessage, logger.message)
        assertEquals("LoggerTest", logger.tag)
        assertEquals(LogLevel.ERROR, logger.logLevel)
        assertEquals(throwable, logger.throwable)
    }
}