rootProject.name = "JavaTempSolution"

include(":BasicWebApp", ":ConsoleApp", ":SpringBootApp")

project(":BasicWebApp").projectDir = File("$rootDir/BasicWebApp")
project(":ConsoleApp").projectDir = File("$rootDir/ConsoleApp")
project(":SpringBootApp").projectDir = File("$rootDir/SpringBootApp")
