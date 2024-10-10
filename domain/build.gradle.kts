plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.coroutines.test)
    implementation(libs.javaxAnnotation)
    implementation(libs.javaxInject)
    testImplementation(libs.junit)
}
