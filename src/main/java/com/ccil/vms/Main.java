package com.ccil.vms;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;

import com.ccil.vms.model.Employee;
import com.ccil.vms.model.EmployeeName;
import com.ccil.vms.model.PageMaster;
import com.ccil.vms.model.Role;
import com.ccil.vms.model.User;
import com.ccil.vms.model.UserIdentity;
import com.ccil.vms.model.Vendor;
import com.ccil.vms.repository.EmployeeRepository;
import com.ccil.vms.repository.PageRepository;
import com.ccil.vms.repository.RoleRepository;
import com.ccil.vms.repository.UserIdentityRepository;
import com.ccil.vms.repository.UserRepository;
import com.ccil.vms.repository.VendorRepository;
import com.ccil.vms.service.UserIdentityService;
@EnableAutoConfiguration
@SpringBootApplication
@EnableAsync
public class Main implements CommandLineRunner{

	@Autowired
	DataSource dataSource;
	@Autowired
	UserRepository customerRepository;
	@Autowired
	VendorRepository vendorRepo;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	UserIdentityRepository userIdentityRepository;
	@Autowired
	RoleRepository rolerepo;
	@Autowired
	PageRepository pagerepo;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

	}

	@Transactional(readOnly = true)
	@Override
	public void run(String... args) throws Exception {

//		System.out.println("DATASOURCE = " + dataSource);
//		List<Role> roles = new ArrayList<Role>();
////		System.out.println("\n1.findAll()...");
//		Role r1 = new Role(); r1.setName("ADMIN"); r1.setDiscription("ADMINISTATION"); 
//		Role r2 = new Role(); r2.setName("USER"); r2.setDiscription("END USER"); 
//		Role r3 = new Role(); r3.setName("DBA"); r3.setDiscription("DATABASE ADMIN"); 
////
////
//		Vendor v1 = new Vendor();v1.setVendorName("TATA COUNSULTANCY SERVICE");v1.setVendorDomain("tcs.com");v1.setWebsite("www.tcs.com");v1.setActiveStatus("ACTIVE");v1.setOfficialemailId("tcshelp@tcs.com");
//		Vendor v2 = new Vendor();v2.setVendorName("CLEARING CORPORATION OF INDIA");v2.setVendorDomain("ccilindia.co.in");v2.setWebsite("www.ccilindia.com");v2.setActiveStatus("ACTIVE");v2.setOfficialemailId("ccilhelp@tcs.com");
//
//		vendorRepo.save(v1);
//		vendorRepo.save(v2);
//
//
//		System.out.println(rolerepo.saveAndFlush(r3));
//		System.out.println(rolerepo.saveAndFlush(r1));
//		System.out.println(rolerepo.saveAndFlush(r2));
////
//		PageMaster p1 = new PageMaster();p1.setPageUrl("profile");p1.setRole(r1);p1.setPageName("Profile");p1.setPageSubName("ViewProfile");p1.setPageIcon("icon_profile");
//		PageMaster p2 = new PageMaster();p2.setPageUrl("profile");p2.setRole(r2);p2.setPageName("Profile");p2.setPageSubName("ViewProfile");p2.setPageIcon("icon_profile");
//		PageMaster p3 = new PageMaster();p3.setPageUrl("vendorList");p3.setRole(r1);p3.setPageName("Vendors");p3.setPageSubName("View Vendors");p3.setPageIcon("icon_table");
//
//		
//		pagerepo.save(p1);
//		pagerepo.save(p2);
//		pagerepo.save(p3);
//
//
//		roles.add(r1);
//		
//		User u1 = new User(); u1.setLockStatus("N"); u1.setPassword("$2a$10$X1XLcM2mpdW/sJQBgszWcuWd5KZU4N.VSfO73TBstBh7UHaj76zlW"); u1.setRoles(roles); u1.setUsername("KHAJAA3");u1.setEmail("skkhaja429@tcs.com");
//		UserIdentity userIdentity = new UserIdentity();
//		userIdentity.setEmail("skkhaja429@tcs.com");
//		userIdentity.setEnabled("Y");
//		userIdentity.setConfirmationToken("4290");
//		userIdentityRepository.saveAndFlush(userIdentity);
//		System.out.println("userIdentity"+userIdentity);
//		customerRepository.saveAndFlush(u1); 
//		customerRepository.findByUsername("KHAJAA3").getRoles().forEach(r -> System.out.println(r.getDiscription()));
//		EmployeeName en1= new EmployeeName();en1.setFirstName("KHWAJA");en1.setLastName("SHAIK");
//		Employee e1 = new Employee();e1.setAlternateEmail("skkhaja429@tcs.com");e1.setFirstName("KHWAJA");e1.setLastName("SHAIK");e1.setVendor(v1);e1.setUserIdentity(userIdentity);
//		employeeRepository.saveAndFlush(e1);
//
//		System.out.println("Done!");
//
//		//exit(0);
	}

}
