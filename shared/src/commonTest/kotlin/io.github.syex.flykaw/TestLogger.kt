package io.github.syex.flykaw

import kotlinx.atomicfu.atomic

class TestLogger : Logger {

    val logLevel get() = logLevelRef.value
    val tag get() = tagRef.value
    val message get() = messageRef.value
    val throwable get() = throwableRef.value

    private var logLevelRef = atomic<LogLevel?>(null)
    private var tagRef = atomic<String?>(null)
    private var messageRef = atomic<String?>(null)
    private var throwableRef = atomic<Throwable?>(null)

    override fun log(level: LogLevel, tag: String, msg: String, throwable: Throwable?) {
        logLevelRef.value = level
        tagRef.value = tag
        messageRef.value = msg
        throwableRef.value = throwable
    }
}