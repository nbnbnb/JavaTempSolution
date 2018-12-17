import com.github.jengelman.gradle.plugins.shadow.ShadowApplicationPlugin
import com.github.jengelman.gradle.plugins.shadow.ShadowExtension
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.java.archives.internal.DefaultManifest

plugins {
    id("com.github.johnrengelman.shadow")
}

description = "BasicConsole App"

dependencies {
    compile("com.microsoft.sqlserver:sqljdbc4:4.0")
    compile("org.aspectj:aspectjweaver")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    compile("org.springframework:spring-orm")
}


tasks {

    /*
        named<ShadowJar>("shadowJar") {
        baseName = "BasicConsoleApp"
        classifier = ""
        version = ""
        manifest {
            attributes("Main-Class" to "basicconsoleapp.Main")
        }
    }
    named<Assemble>("assemble") {
        dependsOn(shadowJar)
    }

     */

    shadowJar {
        baseName = "BasicConsoleApp"
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
