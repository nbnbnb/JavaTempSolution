rootProject.name = "JavaTempSolution"

include("BasicConsoleApp", "SBConsoleApp", "SBWebApp", "Java8ConsoleApp")

project(":BasicConsoleApp").projectDir = file("$rootDir/BasicConsoleApp")
project(":SBConsoleApp").projectDir = file("$rootDir/SBConsoleApp")
project(":SBWebApp").projectDir = file("$rootDir/SBWebApp")
project(":Java8ConsoleApp").projectDir = file("$rootDir/Java8ConsoleApp")
