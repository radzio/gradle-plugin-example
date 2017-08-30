package com.jhood

import com.sendgrid.*
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class MyTask extends DefaultTask {
    File outputFile = new File(project.buildDir, "myfile.txt")

    @TaskAction
    def action() {
        outputFile.parentFile.mkdirs()
        outputFile.createNewFile()
        outputFile.text = project.extensions.myplugin.fileContent


        Email from = new Email("androiddev@talixo.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("piekarz@talixo.de");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(project.extensions.myplugin.fileContent);
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
