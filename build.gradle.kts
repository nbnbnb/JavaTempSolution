import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // 替代原始的 maven 插件
    // https://docs.gradle.org/current/userguide/publishing_maven.html
    // https://docs.gradle.org/current/userguide/maven_plugin.html
    `maven-publish`

    // https://docs.gradle.org/current/userguide/java_plugin.html
    java

    // https://plugins.gradle.org/plugin/org.jetbrains.kotlin.jvm
    // https://kotlinlang.org/docs/reference/using-gradle.html
    kotlin("jvm") version "1.3.41"

    // noarg 和 allopen 需要 apply 之后，才能使用 noArg 和 allOpen 配置
    // 所以此处需要全局 apply
    // https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.noarg
    id("org.jetbrains.kotlin.plugin.noarg") version "1.3.41"
    // https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.allopen
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.41"

    // https://plugins.gradle.org/plugin/org.springframework.boot
    id("org.springframework.boot") version "2.1.6.RELEASE" apply false
    // https://kotlinlang.org/docs/reference/compiler-plugins.html
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.41" apply false
    // A Gradle plugin that provides Maven-like dependency management functionality
    // https://github.com/spring-gradle-plugins/dependency-management-plugin
    id("io.spring.dependency-management") version "1.0.6.RELEASE" apply false

    // 打包完整可运行 jar 包（SpringBoot 插件自带此功能）
    // https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow
    // BasicConsoleApp 和 Java8ConsoleApp 使用此插件打包完整 jar
    id("com.github.johnrengelman.shadow") version "5.1.0" apply false
}

// 读取 kts 脚本相关的类文件
// 需要这个仓库
repositories {
    mavenLocal()
    maven { url = uri("http://maven.aliyun.com/nexus/content/groups/public/") }
    //mavenCentral()
}

subprojects {
    if (name == "SBConsoleApp") {
        description = "SpringBoot ConsoleApp"
        // 需要 apply 这个插件
        // 这样才会有 Spring Boot 的任务
        // 例如，打包完整 jar 包任务：bootJar
        apply(plugin = "org.springframework.boot")
    }

    if (name == "SBWebApp") {
        description = "SpringBoot WebApp"
        apply(plugin = "org.springframework.boot")
    }

    if (name == "BasicConsoleApp") {
        description = "BasicConsole WebApp"
        apply(plugin = "com.github.johnrengelman.shadow")

        tasks.withType<ShadowJar> {
            baseName = "BasicConsoleApp"
            classifier = ""
            version = ""
            manifest {
                attributes("Main-Class" to "basicconsoleapp.Main")
            }
        }

        tasks.withType<Assemble> {
            dependsOn("shadowJar")
        }
    }

    if (name == "Java8ConsoleApp") {
        description = "Java8 Console WebApp"
        apply(plugin = "com.github.johnrengelman.shadow")

        tasks.withType<ShadowJar> {
            baseName = "Java8ConsoleApp"
            classifier = ""
            version = ""
            manifest {
                attributes("Main-Class" to "java8consoleapp.Main")
            }
        }

        tasks.withType<Assemble> {
            dependsOn("shadowJar")
        }
    }

    group = "me.zhangjin.study"
    version = "0.0.1-SNAPSHOT"

    // 如果有 Java 代码，则需要此插件
    apply(plugin = "java")

    // 将 kotlin 编译为 JVM 平台代码
    apply(plugin = "kotlin")

    // 使用 maven-publish 插件
    apply(plugin = "maven-publish")

    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "kotlin-allopen")
    apply(plugin = "kotlin-noarg")

    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")

    // allOpen 的配置
    allOpen {
        // 添加这些注解的类将会 open
        // annotation("com.my.Annotation")
        // annotations("com.another.Annotation", "com.third.Annotation")
    }

    // noArg 的配置
    noArg {
        // 添加这些注解的类将会生成无参的构造函数
        // annotation("com.my.Annotation")
        // annotations("com.another.Annotation", "com.third.Annotation")
    }

    dependencies {

        if (name == "SBConsoleApp") {
            compile("org.springframework.boot:spring-boot-starter")
            compile("org.springframework.boot:spring-boot-starter-data-jpa")
            compile("mysql:mysql-connector-java")
        }

        if (name == "SBWebApp") {
            compile("org.springframework.boot:spring-boot-starter-web")
        }

        if (name == "BasicConsoleApp") {
            // version
            compile("com.microsoft.sqlserver:sqljdbc4:4.0")
            compile("org.aspectj:aspectjweaver")
            compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
            compile("org.springframework:spring-orm")
        }

        implementation(kotlin("stdlib-jdk8"))

        compile("com.fasterxml.jackson.module:jackson-module-kotlin")
        compile("io.reactivex.rxjava2:rxjava")

        compile("org.jetbrains.kotlin:kotlin-stdlib")
        compile("org.jetbrains.kotlin:kotlin-reflect")

        // https://github.com/Kotlin/kotlinx.coroutines
        compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")

        compile("org.springframework:spring-context")

        testCompile("org.springframework.boot:spring-boot-starter-test")
        testCompile("org.jetbrains.kotlin:kotlin-test-junit")
    }

    // 配置 Java 的字节码版本和源代码兼容 JDK 版本
    configure<JavaPluginConvention> {
        if (name == "Java8ConsoleApp") {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        } else {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    // 所有子项目
    // kotlin 的目标 JVM 字节码版本
    // 自定义参数
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
    }

    // 所有子项目
    // Java 源代码编码格式
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    // 所有子项目
    // 项目中的类文件，需要这个仓库
    repositories {
        mavenLocal()
        maven { url = uri("http://maven.aliyun.com/nexus/content/groups/public/") }
        //mavenCentral()
    }

    // 所有子项目
    // 使用 SpringBoot 的包依赖管理

    // spring-boot-dependencies 自带的版本
    // https://github.com/spring-projects/spring-boot/blob/v2.1.3.RELEASE/spring-boot-project/spring-boot-dependencies/pom.xml
    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }
}