package com.ccil.vms.controller;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ccil.vms.CustomSuccessHandler;
import com.ccil.vms.events.OnRegistrationCompleteEvent;
import com.ccil.vms.model.PageMaster;
import com.ccil.vms.model.Role;
import com.ccil.vms.model.User;
import com.ccil.vms.model.UserIdentity;
import com.ccil.vms.service.PageMasterService;
import com.ccil.vms.service.RoleService;
import com.ccil.vms.service.SecurityService;
import com.ccil.vms.service.UserIdentityService;
import com.ccil.vms.service.UserService;
import com.ccil.vms.validator.UserIdentityValidator;
import com.ccil.vms.validator.UserValidator;
@Controller
@SessionAttributes("userIdentity")
public class UserController {
	@Autowired
	private UserService				userService;
	@Autowired
	private UserIdentityService		userIdentityService;
	@Autowired
	private SecurityService			securityService;
	@Autowired
	private PageMasterService		pageMasterService;
	@Autowired
	private RoleService				roleService;

	
	
	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login(Model model, String error, String logout) {
//		if (error != null)
//			model.addAttribute("error", "Your username and password is invalid.");
//		if (logout != null)
//			model.addAttribute("message", "You have been logged out successfully.");
//		return "login";
//	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView modelAndView, String error, String logout) {
		if (error != null)
			modelAndView.addObject("error", "Your username and password is invalid.");
		if (logout != null)
			modelAndView.addObject("message", "You have been logged out successfully.");
		modelAndView.setViewName("login");

		return modelAndView;
	}
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request, HttpServletResponse response) {
		securityService.redirect(request, response);
		return "dashbord";
	}
	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "dashbord";
	}
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "dashbord";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "dashbord";
	}
	

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(Model model) {
		return "accessDenied";
	}
	@ModelAttribute("roles")
	public List<Role> getRoleList() {
		return userService.findAllRoles();
	}
	@ModelAttribute("pages")
	public Map<String, List<PageMaster>> getPageListbyRole() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<PageMaster> pages = new ArrayList<PageMaster>();
		auth.getAuthorities().forEach(s -> {
			Role role = roleService.findByName(s.getAuthority().substring(5));
			pageMasterService.findByRole(role).forEach(s1 -> {
				pages.add(s1);
			});
		});
		System.out.println("yoo");

		Map<String, List<PageMaster>> dataGroupByPageName = pages.stream().collect(Collectors.groupingBy(PageMaster::getPageName));

		dataGroupByPageName.forEach((key, value) -> {
			System.out.print(key+"\t");
			value.stream().sorted(Comparator.comparing(PageMaster::getPageName)).forEach(System.out::println);
			System.out.println();
		});
		
		return dataGroupByPageName;
	}
	
	
	 @ModelAttribute("userIdentity")
	   public UserIdentity setUpUserForm() {
	      return new UserIdentity();
	   }
	 @ModelAttribute("currentUser")
	   public User getCurrentUser() {
	      return userService.findByEmail(userIdentityService.getPrincipal());
	   }
}
