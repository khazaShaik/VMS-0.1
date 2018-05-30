package com.ccil.vms.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ccil.vms.model.Employee;
import com.ccil.vms.model.User;
import com.ccil.vms.model.UserIdentity;
import com.ccil.vms.model.Vendor;
import com.ccil.vms.service.EmployeeService;
import com.ccil.vms.service.UserIdentityService;
import com.ccil.vms.service.UserService;
import com.ccil.vms.service.VendorService;
@SessionAttributes("userIdentity")
@Controller
public class ProfileController {

	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private UserIdentityService		userIdentityService;
	
	@Autowired
	private UserService		userService;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model) {
		User user = userService.findByEmail(userIdentityService.getPrincipal());
		model.addAttribute("user",user);
		return "profile";
	}
	
	@RequestMapping(value = "/updateAbout/{id}", method = RequestMethod.POST)
	public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model ,@PathVariable long id) {
		if (bindingResult.hasErrors()) {
			return "redirect:/profile";
		}
		employeeService.updateEmployee(user.getEmployee(),id);
		return "redirect:/profile";
	}
		
	@RequestMapping(value = "/vendorList", method = RequestMethod.GET)
	public String editProfile( Model model) {
		List<Vendor> list = vendorService.findAllVendors();
		list.forEach(v -> {System.out.println(v.getActiveStatus());});
		model.addAttribute("vendorList",list);
		return "profile";
	}
	
	@ModelAttribute("userIdentity")
	public UserIdentity getUserIdentity() {
		UserIdentity userIdentity=userIdentityService.findByEmail(UserDetails.class.getName());
		if(userIdentity==null){
			userIdentity=new UserIdentity();
		}
		return userIdentity;
	}
	
	@ModelAttribute("countries")
	public List<Locale> getCountryList() {
		List<Locale> countries = new ArrayList<Locale>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
		    Locale obj = new Locale("", countryCode);
		    countries.add(obj);		    
//		    System.out.println("Country Code = " + obj.getCountry()
//			+ ", Country Name = " + obj.getDisplayCountry(Locale.ENGLISH));
		 }
		return countries;
		
	}
	
}
