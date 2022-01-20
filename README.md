# flykaw
![](https://github.com/Syex/flykaw/workflows/build/badge.svg?branch=main)
![](https://img.shields.io/github/v/release/Syex/flykaw)

flykaw is **a** **w**rapper **f**or **y**our **K**otlin **l**ogger, in short *awfykl* or as an anagram _flykaw_.

## Why?
Imagine you're using [Timber](https://github.com/JakeWharton/timber) and have Timber calls in all your code:

```kotlin
class HelloWorld {

    init {
      Timber.i("Hello World!")
    }

    fun greet() {
      Timber.d("Greetings!")
    }
}
```

Now that fancy new logging tool has been released and you'd like to replace Timber, what means you have to touch every class and replace all Timber calls.

With flykaw you only have one place where you would be using Timber instead:

```kotlin
// Create your own Logger implementation
class TimberLogger : Logger {

    override fun log(level: LogLevel, tag: String, msg: String, throwable: Throwable?) {
      Timber.log(level.ordinaryValue, throwable, msg)
    }
}

// Make your logger available to flykaw
LogConfig.setLogger(TimberLogger())

class HelloWorld {

  init {
    // use flykaw's logging methods
    logInfo("Hello World!")
  }

  fun greet() {
    logDebug("Greetings!")
  }
}
```

Now, you would only need to provide a different logger to `LogConfig.setLogger()` and you would be done.

## Download
flykaw is available on `mavenCentral()` as a KMM library, so you can either add it to your `commonMain` source set or as usual as an Android library:

```kotlin
dependencies {
    implementation("io.github.syex:flykaw:$version")
}
```