package me.zhangjin.demos.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SimpleTask : DefaultTask() {

    @TaskAction
    fun start() {
        System.out.println("haha")
    }
}

