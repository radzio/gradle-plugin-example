package net.droidlabs.gradle.sendgrid

import org.gradle.api.Plugin
import org.gradle.api.Project

class SendGridPlugin implements Plugin<Project> {
  void apply(Project project) {

    project.extensions.add("sendgrid", SendGridPluginExtension)


    // The "right" way
    project.task("sendEmail", type: SendEmailTask) {
      group = "sendgrid"
      description = "Send email"
    }
  }
}
