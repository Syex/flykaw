package io.github.syex.flykaw

import io.github.syex.flykaw.LogConfig.logLevel
import io.github.syex.flykaw.LogConfig.logger
import io.github.syex.flykaw.LogConfig.setLogger
import kotlinx.atomicfu.atomic

/**
 * Any logging calls will be delegated to the [logger] instance set in this object via [setLogger].
 * Additionally, the granularity can be configured via [logLevel].
 */
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
