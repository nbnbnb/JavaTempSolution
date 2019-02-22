tasks {
    shadowJar {
        baseName = "Java8ConsoleApp"
        classifier = ""
        version = ""
        manifest {
            attributes("Main-Class" to "basicconsoleapp.Main")
        }
    }

    assemble {
        dependsOn(shadowJar)
    }
}