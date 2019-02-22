tasks {
    shadowJar {
        baseName = "Java8ConsoleApp"
        classifier = ""
        version = ""
        manifest {
            attributes("Main-Class" to "java8consoleapp.Main")
        }
    }

    assemble {
        dependsOn(shadowJar)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}


