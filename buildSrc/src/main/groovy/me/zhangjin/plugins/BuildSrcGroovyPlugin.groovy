package me.zhangjin.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildSrcGroovyPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task('hello02') {
            group = "custom"
            description = "buildSrc - groovy 自定义插件"
            doLast {
                println "Hello from the BuildSrcGroovyPlugin"
            }
        }
    }
}