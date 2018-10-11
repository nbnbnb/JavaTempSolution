package me.zhangjin.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project


class BuildSrcKotlinPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.task("hello05") {
            it.description = "buildSrc - Kotlin �Զ�����"
            it.group = "custom"
            it.doLast {
                println("Hello from the BuildSrcKotlinPlugin")
            }
        }
    }

}
