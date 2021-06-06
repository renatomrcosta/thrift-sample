import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "com.xunfos"
version = "0.0.1"

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
repositories {
    mavenCentral()
    jcenter()
    maven {
        setUrl("https://kotlin.bintray.com/ktor")
    }
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("src/main/gen-java")
    }
}


dependencies {
    val armeriaVersion = "1.8.0"
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.apache.thrift:libthrift:0.13.0")

    implementation("com.linecorp.armeria:armeria:$armeriaVersion")
    implementation("com.linecorp.armeria:armeria-thrift0.13:$armeriaVersion")

}
