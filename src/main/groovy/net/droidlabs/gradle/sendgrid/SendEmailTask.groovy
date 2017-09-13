package net.droidlabs.gradle.sendgrid

import com.sendgrid.*
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SendEmailTask extends DefaultTask {
    @TaskAction
    def action() {

        Email from = new Email(project.extensions.sendgrid.fromEmail);
        String subject = project.extensions.sendgrid.subject;

        for(email in project.extensions.sendgrid.toEmail) {
            Email to = new Email(email);
            Content content = new Content("text/html", project.extensions.sendgrid.content);
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(project.extensions.sendgrid.sendgridApiKey);
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            } catch (IOException ex) {
                throw ex;
            }
        }
    }
}
