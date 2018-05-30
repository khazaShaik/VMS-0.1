package com.ccil.vms.serviceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ccil.vms.CustomSuccessHandler;
import com.ccil.vms.model.User;
import com.ccil.vms.service.SecurityService;
import com.ccil.vms.service.UserService;
@Service
public class SecurityServiceImpl implements SecurityService {
	@Autowired
	private AuthenticationManager	authenticationManager;
	@Autowired
	private UserDetailsService		userDetailsService;
	@Autowired
	private UserService				userService;
	@Autowired
	private CustomSuccessHandler  customSuccessHandler;
	
	
	
	
	
	@Override
	public String findLoggedInUsername() {
		User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}
		return null;
	}
	@Override
	public UserDetails findLoggedInUser() {
		UserDetails loginUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return loginUser;
	}
	@Override
	public void autologin(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		 
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, getAuthority(username));
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}
	@Override
	public List<GrantedAuthority> getAuthority(String userName) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.addAll(userService.getAuthorities(userName));
		return grantedAuthorities;
	}
	@Override
	public void updatelogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),"",getAuthority(auth.getName()));
		//authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		
	}
	@Override
	public void redirect(HttpServletRequest request,HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			customSuccessHandler.onAuthenticationSuccess(request, response, auth);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
