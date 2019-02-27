rootProject.name = "JavaTempSolution"

include("SBConsoleApp", "SBWebApp", "BasicConsoleApp", "Java8ConsoleApp")

project(":SBConsoleApp").projectDir = file("$rootDir/SBConsoleApp")
project(":SBWebApp").projectDir = file("$rootDir/SBWebApp")
project(":BasicConsoleApp").projectDir = file("$rootDir/BasicConsoleApp")
project(":Java8ConsoleApp").projectDir = file("$rootDir/Java8ConsoleApp")

pluginManagement {
    resolutionStrategy {
    }
    repositories {
        mavenLocal()
        maven { url = uri("http://maven.aliyun.com/nexus/content/groups/public/") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
}