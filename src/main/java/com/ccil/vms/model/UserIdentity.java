package com.ccil.vms.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import com.ccil.vms.bean.UserIndentity;
@Entity
@Table(name = "VMS_USER_IDENTITY")
public class UserIdentity implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Long				id;
	private String				email;
	private String				confirmationToken;
	private String				enabled= "N";
	public UserIdentity() {
		super();
	}
	public UserIdentity(UserIndentity userIdentity) {
		email = userIdentity.getEmail();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Email
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	@Column(unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfirmationToken() {
		return confirmationToken;
	}
	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	@Transient
	public boolean isUserEnabled() {
		return getEnabled().equalsIgnoreCase("N") ? false : true;
	}
	@Transient
	public void enable(boolean b) {
		if(b){setEnabled("Y");}else{setEnabled("N");};	
	}
}
