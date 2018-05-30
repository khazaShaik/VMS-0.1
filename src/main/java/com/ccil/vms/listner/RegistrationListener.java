package com.ccil.vms.listeners;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.ccil.vms.events.OnRegistrationCompleteEvent;
import com.ccil.vms.model.UserIdentity;
import com.ccil.vms.service.UserIdentityService;

@Component
public class RegistrationListener implements
  ApplicationListener<OnRegistrationCompleteEvent> {
  
    @Autowired
    private UserIdentityService service;
  
    @Autowired
    private MessageSource messages;
  
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserIdentity userIdentity = event.getUserIdentity();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(userIdentity, token);
         
        String recipientAddress = userIdentity.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = event.getAppUrl() + "/emailRegitrationConfirm?token=" + token;
        System.out.println(confirmationUrl);
        String message = messages.getMessage("message.regSucc", null, event.getLocale());
         
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
    //    mailSender.send(email);
        System.out.println("Mail sent");
    }
}
