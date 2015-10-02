package com.ideas.game

import grails.core.GrailsApplication
import org.apache.commons.mail.SimpleEmail;


class EmailService {

    GrailsApplication grailsApplication

    public EmailService(GrailsApplication grailsApplication){
        this.grailsApplication=grailsApplication;
    }
    def sendMail(String toEmail, String subject, String body){
        SimpleEmail email = new SimpleEmail()
        email.setHostName(grailsApplication.config.getProperty('email.host'));
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
