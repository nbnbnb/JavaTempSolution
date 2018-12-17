plugins {
    id("org.springframework.boot")
}

description = "SpringBoot ConsoleApp"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("mysql:mysql-connector-java")

    testCompile("org.springframework.boot:spring-boot-starter-test")
}


