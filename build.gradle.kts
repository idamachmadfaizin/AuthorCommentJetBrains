plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "2.1.0"
  id("org.jetbrains.intellij.platform") version "2.2.1"
}

group = property("pluginGroup").toString()
version = property("pluginVersion").toString()

repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
    localPlatformArtifacts()
  }
}

dependencies {
  // Configure Gradle IntelliJ Plugin
  // Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
  intellijPlatform {
    local(providers.gradleProperty("platformPath"))
  }
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = property("javaVersion").toString()
    targetCompatibility = property("javaVersion").toString()
  }

  patchPluginXml {
    sinceBuild.set(providers.gradleProperty("patchPluginSinceBuild"))
    untilBuild.set(providers.gradleProperty("patchPluginUntilBuild"))
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}
