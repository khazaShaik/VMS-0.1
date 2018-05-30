package com.ccil.vms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ccil.vms.model.GoogleAuth;
import com.ccil.vms.model.User;
import com.ccil.vms.service.SecurityService;
import com.ccil.vms.service.UserIdentityService;
import com.ccil.vms.service.UserService;
import com.ccil.vms.validator.GoogleAuthValidator;

@Controller
@RequestMapping("/code")
public class VerificationCodeController {
	
	@Autowired
	GoogleAuthValidator googleAuthValidator	; 
	
	@Autowired
	private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserIdentityService userIdentityService;

    @RequestMapping(method = RequestMethod.POST)
    public String verifyCode(@ModelAttribute("googleAuth") GoogleAuth googleAuth, BindingResult bindingResult, Model model) {
    //	googleAuthValidator.validate(googleAuth, bindingResult);
    	if (bindingResult.hasErrors()) {
              return "code";
          }
    	securityService.updatelogin();
    	return "redirect:/home";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showVerificationCodeForm(Model model) {
    	String userName= userIdentityService.getPrincipal();
    	User user = userService.findByEmail(userName);
    	if (user.getGoogleAuth()!= null){
     		model.addAttribute("googleAuth",  user.getGoogleAuth());	
    	}else {
     		model.addAttribute("googleAuth",  new GoogleAuth());	

    	}
        return "code";
    }

    
}
