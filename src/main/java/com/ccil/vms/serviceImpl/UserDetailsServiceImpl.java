package com.ccil.vms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccil.vms.model.LoggedUser;
import com.ccil.vms.model.User;
import com.ccil.vms.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		if (user == null) {
//			throw new UsernameNotFoundException("No user found with username: " + username);
//		} else {
//			return new UserDetailsAdapter(user ,"ROLE_PRE_AUTH_USER");
//		}
//	}
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
			if(user==null){
				throw new UsernameNotFoundException("User doesn`t exist");
			}
			List<String> dbRoles=new ArrayList<String>();
			dbRoles.add("ROLE_PRE_AUTH_USER");
			LoggedUser loggedUser=new LoggedUser(user, dbRoles);
			return loggedUser;
		}

}
