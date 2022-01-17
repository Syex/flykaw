package io.github.syex.flykaw

import kotlinx.atomicfu.atomic

object LogConfig {

    val logLevel: LogLevel get() = logLevelRef.value
    val logger: Logger? get() = loggerRef.value

    private val logLevelRef = atomic(LogLevel.VERBOSE)
    private val loggerRef = atomic<Logger?>(null)

    fun setLogLevel(logLevel: LogLevel) {
        logLevelRef.value = logLevel
    }

    fun setLogger(logger: Logger?) {
        loggerRef.value = logger
    }
}
