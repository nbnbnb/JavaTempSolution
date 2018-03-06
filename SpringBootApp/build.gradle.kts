plugins {
    id("org.springframework.boot") version ("1.5.10.RELEASE")
}

description = "The SpringBootApp"
version = "0.1.0-SNAPSHOT"

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}
