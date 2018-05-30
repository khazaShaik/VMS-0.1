package com.ccil.vms.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ccil.vms.events.OnRegistrationCompleteEvent;
import com.ccil.vms.model.Employee;
import com.ccil.vms.model.GoogleAuth;
import com.ccil.vms.model.Role;
import com.ccil.vms.model.User;
import com.ccil.vms.model.UserIdentity;
import com.ccil.vms.model.Vendor;
import com.ccil.vms.service.EmployeeService;
import com.ccil.vms.service.GoogleAuthService;
import com.ccil.vms.service.RoleService;
import com.ccil.vms.service.SecurityService;
import com.ccil.vms.service.UserIdentityService;
import com.ccil.vms.service.UserService;
import com.ccil.vms.service.VendorService;
import com.ccil.vms.validator.GoogleAuthValidator;
import com.ccil.vms.validator.UserIdentityValidator;
import com.ccil.vms.validator.UserValidator;
@Controller
@SessionAttributes("userIdentity")
public class RegistrationController {

	
	@Autowired
	private UserValidator			userValidator;
	@Autowired
	private UserIdentityValidator	userIdentityValidator;
	@Autowired
	private GoogleAuthValidator	googleAuthValidator;
	@Autowired
	private UserService				userService;
	@Autowired
	private UserIdentityService		userIdentityService;
	@Autowired
	private SecurityService			securityService;
	@Autowired
	private ApplicationEventPublisher		eventPublisher;
	@Autowired
	private GoogleAuthService googleAuthService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RoleService				roleService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(@SessionAttribute("userIdentity") UserIdentity userIdentity, Model model) {
		userIdentity = userIdentityService.findByEmail(userIdentity.getEmail()); 
		User user = userService.findByEmail(userIdentity.getEmail());
		if(user != null){
			return "redirect:/welcome";
		}
			
		if(userIdentity.isUserEnabled()){
			model.addAttribute("userForm", new User());
			return "registration";
		}else{
			return "redirect:/welcome";
		}
	}
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@SessionAttribute("userIdentity") UserIdentity userIdentity ,@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		Employee emp =new Employee();
		emp.setUserIdentity(userIdentityService.findByEmail(userIdentity.getEmail()));
		employeeService.saveEmployee(emp);
		userService.save(userForm);
		securityService.autologin(userForm.getEmail(), userForm.getPasswordConfirm());
		return "redirect:/SetGoogleAuth";
	}
	
	@RequestMapping(value = "/SetGoogleAuth", method = RequestMethod.GET)
	public String SetGoogleAuth( Model model) {
		User user = userService.findByEmail(userIdentityService.getPrincipal()); 
		if(user!=null){
			GoogleAuth googleAuth = googleAuthService.createGoogleAuth(userIdentityService.getPrincipal());
			model.addAttribute("googleAuth", googleAuth);
			return "SetGoogleAuth";
		}else{
			return "redirect:/registration";
		}
	}
	@RequestMapping(value = "/SetGoogleAuth", method = RequestMethod.POST)
	public String SetGoogleAuth(@ModelAttribute("googleAuth") GoogleAuth googleAuth, BindingResult bindingResult, Model model) {
		googleAuthValidator.validate(googleAuth, bindingResult);
		if (bindingResult.hasErrors()) {
			return "SetGoogleAuth";
		}
		googleAuthService.save(googleAuth);
		User user =userService.findByEmail(googleAuth.getGoogleAuthUser());
		if(user!= null){
			user.setGoogleAuth(googleAuth);
			userService.save(user);
		}
		return "redirect:/home";
	}
	
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("userIdentity", new UserIdentity());
		return "app.welcomepage";
	}
	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public String welcome(@ModelAttribute("userIdentity") UserIdentity userIdentity, WebRequest request, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		userIdentityValidator.validate(userIdentity, bindingResult);
		String returnURL = "app.welcomepage";
		String appUrl = request.getContextPath();
		try {
			if (bindingResult.hasErrors()) {
				return returnURL;
			} else if (userService.findByEmail(userIdentity.getEmail().trim()) != null) {
				bindingResult.rejectValue("email", "user.email.already.registered");
				returnURL = "app.welcomepage";
			} else {
					userIdentity = userIdentityService.saveRegisteredUser(userIdentity);
					eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userIdentity, request.getLocale(), appUrl));
					returnURL = "redirect:/emailVerify";
					
			}
		} catch (Exception me) {
			System.out.println(me);
			bindingResult.rejectValue("email", "userIdentity.email.invalid");
			return "app.welcomepage";
		}
		return returnURL;
		// securityService.autologin(userForm.getUsername(),
		// userForm.getPasswordConfirm());
	}
	
	
	
	@ModelAttribute("userIdentity")
	public UserIdentity getUserIdentity() {
		UserIdentity userIdentity=userIdentityService.findByEmail(UserDetails.class.getName());
		if(userIdentity==null){
			userIdentity=new UserIdentity();
		}
		return userIdentity;
	}
	
	@ModelAttribute("dfaultRoles")
	public List<Role> getRoleList() {
		return roleService.findAllByName("USER");
		
	}
	
	
	
	
	
}
