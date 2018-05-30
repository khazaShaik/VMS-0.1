package com.ccil.vms.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * 
 * @author Srinivas Nalla
 *
 */
public class LoggedUser implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private List<String> roles;
	
	public LoggedUser(User user, List<String> roles){
		this.user=user;
		this.roles=roles;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(roles.stream().toArray(String[]::new));

	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
