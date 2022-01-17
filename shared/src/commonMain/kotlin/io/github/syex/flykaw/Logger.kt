@file:Suppress("unused")

package io.github.syex.flykaw

import io.github.syex.flykaw.LogConfig.logLevel
import io.github.syex.flykaw.LogConfig.logger
import io.github.syex.flykaw.LogLevel.*

/**
 * Extend this interface and set it via [LogConfig.logger] to forward log messages to any
 * logging framework of your choice.
 */
interface Logger {

    /**
     * Logs a new message.
     *
     * @param level one of [VERBOSE], [DEBUG], [INFO], [WARNING] or [ERROR].
     * @param tag  A logging tag.
     * @param msg  The message to log.
     */
    fun log(level: LogLevel, tag: String, msg: String, throwable: Throwable? = null)
}

enum class LogLevel(val ordinaryValue: Int) {

    VERBOSE(2),
    DEBUG(3),
    INFO(4),
    WARNING(5),
    ERROR(6)
}

inline fun <reified T> T.logTag(): String = T::class.simpleName!!

inline fun <reified T : Any> T.logDebug(msg: String, throwable: Throwable? = null) {
    if (logLevel.ordinaryValue > DEBUG.ordinaryValue) return

    logger?.log(DEBUG, logTag(), msg, throwable)
}

inline fun <reified T : Any> T.logError(msg: String, throwable: Throwable? = null) {
    logger?.log(ERROR, logTag(), msg, throwable)
}

inline fun <reified T : Any> T.logInfo(msg: String, throwable: Throwable? = null) {
    if (logLevel.ordinaryValue > INFO.ordinaryValue) return

    logger?.log(INFO, logTag(), msg, throwable)
}

inline fun <reified T : Any> T.logVerbose(msg: String, throwable: Throwable? = null) {
    if (logLevel.ordinaryValue > VERBOSE.ordinaryValue) return

    logger?.log(VERBOSE, logTag(), msg, throwable)
}

inline fun <reified T : Any> T.logWarning(msg: String, throwable: Throwable? = null) {
    if (logLevel.ordinaryValue > WARNING.ordinaryValue) return

    logger?.log(WARNING, logTag(), msg, throwable)
}
