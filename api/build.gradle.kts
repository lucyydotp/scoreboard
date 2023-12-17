plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

java.toolchain.languageVersion = JavaLanguageVersion.of(17)

dependencies {
    api("net.kyori:adventure-api:4.14.0")
    compileOnlyApi("org.jetbrains:annotations:24.1.0")
}
