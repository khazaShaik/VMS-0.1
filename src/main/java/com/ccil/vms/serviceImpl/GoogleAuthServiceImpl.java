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

import com.ccil.vms.model.GoogleAuth;
import com.ccil.vms.model.Role;
import com.ccil.vms.model.User;
import com.ccil.vms.repository.GoogleAuthRepository;
import com.ccil.vms.repository.RoleRepository;
import com.ccil.vms.repository.UserRepository;
import com.ccil.vms.service.GoogleAuthService;
import com.ccil.vms.service.RoleService;
import com.ccil.vms.service.UserService;
import com.ccil.vms.util.TimeBasedOneTimePassword;
@Service
public class GoogleAuthServiceImpl implements GoogleAuthService {
	
	@Autowired
	private GoogleAuthRepository googleAuthRepository;
	
	@Autowired
	TimeBasedOneTimePassword  timebaseOneTimePassword;
	
	@Override
	public void save(GoogleAuth googleAuth) {
		googleAuthRepository.save(googleAuth);
	}
	@Override
	public GoogleAuth findByGoogleAuthUser(String name) {
		return googleAuthRepository.findByGoogleAuthUser(name);
	}
	@Override
	public GoogleAuth createGoogleAuth(String email) {
		String secreat = timebaseOneTimePassword.generateSecreat();
		String url = timebaseOneTimePassword.getQRBarcodeURL(email,"Vms.com",secreat);
		GoogleAuth googleAuth= new GoogleAuth(email,secreat,url);
		return googleAuth;
	}
	
	
}
