package com.ccil.vms.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ccil.vms.model.UserIdentity;
import com.ccil.vms.model.VerificationToken;
import com.ccil.vms.repository.UserIdentityRepository;
import com.ccil.vms.repository.VerificationTokenRepository;
import com.ccil.vms.service.UserIdentityService;
@Service
public class UserIdentityServiceImpl implements UserIdentityService {

	@Autowired
	private VerificationTokenRepository	tokenRepository;
	
	@Autowired
	private UserIdentityRepository userIdentityRepository;
	
	@Override
	public UserIdentity findByEmail(String email) {
		return userIdentityRepository.findByEmail(email);
	}
	
	
	@Override
	public UserIdentity findByConfirmationToken(String confirmationToken) {
		return userIdentityRepository.findByConfirmationToken(confirmationToken);
	}
	
	
	private boolean emailExist(String email) {
		UserIdentity userIdentity = userIdentityRepository.findByEmail(email);
		if (userIdentity != null) {
			return true;
		}
		return false;
	}
	@Override
	public UserIdentity getUserIdentity(String verificationToken) {
		UserIdentity userIdentity = tokenRepository.findByToken(verificationToken).getUserIdentity();
		return userIdentity;
	}
	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}
	@Override
	public UserIdentity saveRegisteredUser(UserIdentity userIdentity) {
		UserIdentity userIdentityDB = userIdentityRepository.findByEmail(userIdentity.getEmail());
		if (userIdentityDB==null) {
			userIdentityRepository.save(userIdentity);
		}else{
			userIdentityDB.setConfirmationToken(userIdentity.getConfirmationToken());
			userIdentity=userIdentityRepository.save(userIdentityDB);

		}
		return userIdentity;
		
	}
	@Override
	public void createVerificationToken(UserIdentity userIdentity, String token) {
		VerificationToken verificationToken = tokenRepository.findByUserIdentity(userIdentity);
		if(verificationToken == null){
			VerificationToken myToken = new VerificationToken(token, userIdentity);
			tokenRepository.save(myToken);
		}else{
			verificationToken.setToken(token);
			tokenRepository.save(verificationToken);
		}
	
	}
	@Override 
	public String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
