package me.zhangjin.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {

        // 添加一个 DSL
        project.extensions.create('custom', MyExtension)

        project.task('hello04') {
            group = "custom"
            description = "jar - groovy 自定义插件"
            doLast {
                println "Hello from the CustomPlugin 2.0.1"
                println "Extension Message ${project.custom.message}"
            }
        }
    }
}