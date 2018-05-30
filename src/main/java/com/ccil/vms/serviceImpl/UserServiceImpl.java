package com.ccil.vms.serviceImpl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ccil.vms.model.Role;
import com.ccil.vms.model.User;
import com.ccil.vms.repository.RoleRepository;
import com.ccil.vms.repository.UserRepository;
import com.ccil.vms.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository				userRepository;
	@Autowired
	private RoleRepository				roleRepository;
	@Autowired
	private BCryptPasswordEncoder		bCryptPasswordEncoder;
	@Override
	public void save(User user) {
		System.out.println(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	@Override
	public User updateUser(User user) {
		User u = userRepository.findOne(user.getId());
		//u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		u.setPassword("$2a$10$X1XLcM2mpdW/sJQBgszWcuWd5KZU4N.VSfO73TBstBh7UHaj76zlW");
		
		userRepository.save(u);
		return u;
	}
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	@Override
	public List<Role> findAllRoles() {
		return roleRepository.findAll().stream().sorted(Comparator.comparing(Role::getDiscription)).collect(Collectors.toList());
	}
	
	@Override
	public List<GrantedAuthority> getAuthorities(String userName) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		User user = userRepository.findByEmail(userName);
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		}
		return authorities;
	}
//	@Override
//	public User registerNewUserAccount(User newuser)
//			throws EmailExistsException {
//		if (emailExist(newuser.getEmail())) {
//			throw new EmailExistsException(
//					"There is an account with that email adress: "
//							+ newuser.getEmail());
//		}
//		
//		return userRepository.save(newuser);
//	}
	private boolean emailExist(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

}
