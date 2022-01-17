plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.vanniktech.maven.publish")
    signing
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:atomicfu:0.17.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 16
        targetSdk = 31
    }
}

publishing {
    repositories {
        val releasesRepoUrl = "https://s01.oss.sonatype.org/content/repositories/releases"
        val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
        val repoUrl = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

        maven(url = repoUrl) {
            authentication {
                credentials {
                    username = System.getenv("OSS_SONATYPE_USERNAME")
                    password = System.getenv("OSS_SONATYPE_PASSWORD")
                }
            }
        }
    }
}

signing {
    val signingPrivateKey = System.getenv("MAVEN_GPG_PRIVATE_KEY")
    val signingPassword = System.getenv("MAVEN_GPG_PASSPHRASE")
    useInMemoryPgpKeys(signingPrivateKey, signingPassword)
    sign(publishing.publications)
}
