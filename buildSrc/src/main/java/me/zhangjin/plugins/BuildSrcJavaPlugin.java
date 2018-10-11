package me.zhangjin.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

class BuildSrcJavaPlugin implements Plugin<Project> {

    /**
     * Apply this plugin to the given target object.
     *
     * @param project The target object
     */
    @Override
    public void apply(Project project) {
        Task task = project.task("hello03");
        task.setDescription("buildSrc - Java 自定义插件");
        task.setGroup("custom");
        task.doLast(it -> System.out.println("Hello from the BuildSrcJavaPlugin"));
    }
}