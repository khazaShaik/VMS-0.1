package com.ccil.vms.controller;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.ccil.vms.bean.EmailAuth;
import com.ccil.vms.model.UserIdentity;
import com.ccil.vms.model.VerificationToken;
import com.ccil.vms.service.UserIdentityService;
import com.ccil.vms.service.UserService;


@Controller
public class VerificationEmailController {
	
	@Autowired
	UserService userService;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	UserIdentityService userIdentityService;
	 
	@Autowired
	 MessageSource messages;

	@RequestMapping(value = "/emailVerify", method = RequestMethod.POST)
    public String verifyCode(@ModelAttribute("emailAuth") EmailAuth emailAuth, BindingResult bindingResult, Model model) {
    	  	
    	if (bindingResult.hasErrors()) {
              return "emailVerify";
          }
       return "redirect:/registration";
    }

    @RequestMapping(value = "/emailVerify",method = RequestMethod.GET)
    public String showVerificationCodeForm(Model model) {
    	model.addAttribute("emailAuth", new EmailAuth());
        return "emailVerify";
    }

	@RequestMapping(value = "/emailRegitrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration
	  (WebRequest request, Model model, @RequestParam("token") String token) {
	  
	    Locale locale = request.getLocale();
	     
	    VerificationToken verificationToken = userIdentityService.getVerificationToken(token);
	    if (verificationToken == null) {
	        String message = messages.getMessage("auth.message.invalidToken", null, locale);
	        model.addAttribute("message", message);
	        return "badUser";
	    }	     
	    UserIdentity userIdentity = verificationToken.getUserIdentity();
	    Calendar cal = Calendar.getInstance();
//	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	    if (false) {

	    	String messageValue = messages.getMessage("auth.message.expired", null, locale);
	        model.addAttribute("message", messageValue);
	        return "badUser";
	    } 
	     
	    userIdentity.enable(true); 
	    userIdentityService.saveRegisteredUser(userIdentity); 
        String messageValue = messages.getMessage("auth.message.token.verified", null, locale);
        model.addAttribute("message", messageValue);
	    return "emailSuccess"; 
	}

    
}
