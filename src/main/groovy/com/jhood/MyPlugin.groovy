package com.jhood

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {
  void apply(Project project) {

    project.extensions.add("myplugin", MyPluginExtension)


    // The "right" way
    project.task("mytask", type: MyTask) {
      group = "MyPlugin"
      description = "Create myfile.txt in the build directory"
    }
  }
}
