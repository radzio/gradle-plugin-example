package net.droidlabs.gradle.sendgrid

class SendGridPluginExtension {
  String fromEmail = ""
  String subject = ""
  String[] toEmail = []
  String content = ""
  String sendgridApiKey = ""
}
