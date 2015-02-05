package com.ideas.game

import org.apache.commons.mail.SimpleEmail;


class EmailService {
    def grailsApplication

    public EmailService(def grailsApplication){
        this.grailsApplication=grailsApplication;
    }
    def sendMail(String toEmail, String subject, String body){
        SimpleEmail email = new SimpleEmail()
        email.setHostName(grailsApplication.config.grails.mail.host)
        email.addTo(toEmail)
        email.setFrom("notification@ideasrockstar.com")
        email.setSubject(subject)
        email.msg = body;
        email.setSmtpPort(25)
        try {
            email.send()
        }catch(Exception e){
            println("Could not send email.",e.getMessage());
        }
    }
}
