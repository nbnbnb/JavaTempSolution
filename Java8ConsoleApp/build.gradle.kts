import com.github.jengelman.gradle.plugins.shadow.ShadowApplicationPlugin
import com.github.jengelman.gradle.plugins.shadow.ShadowExtension
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.java.archives.internal.DefaultManifest

plugins {
    id("com.github.johnrengelman.shadow")
}

description = "Java8Console App"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

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

