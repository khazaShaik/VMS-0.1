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
import com.ccil.vms.service.RoleService;
import com.ccil.vms.service.UserService;
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository				roleRepository;
	
	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}
	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
	@Override
	public List<Role> findAllByName(String name) {
		return roleRepository.findAll().stream().filter(r -> r.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}
	private boolean roleExist(String roleName) {
		Role role = roleRepository.findByName(roleName);
		if (role != null) {
			return true;
		}
		return false;
	}

}
