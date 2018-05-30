package com.ccil.vms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VMS_GOOGLE_AUTH")
public class GoogleAuth  {
	private Long	id;
	private String googleAuthurl;
	private String googleAuthOtp;
	private String googleAuthSecret;
	private String googleAuthUser;
	private String googleAuthHost;
	private String googleAuthFormat;
	
	
	
	public GoogleAuth(String googleAuthUser,String googleAuthSecret,String googleAuthurl) {
		super();
		this.googleAuthUser = googleAuthUser;
		this.googleAuthFormat="https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";
		this.googleAuthSecret =googleAuthSecret;
		this.googleAuthHost="Vms.com";
		this.googleAuthurl=googleAuthurl;
		}
	
	public GoogleAuth() {
		// TODO Auto-generated constructor stub
	}
	public String getGoogleAuthurl() {
		return googleAuthurl;
	}
	public void setGoogleAuthurl(String googleAuthurl) {
		this.googleAuthurl = googleAuthurl;
	}
	public String getGoogleAuthOtp() {
		return googleAuthOtp;
	}
	public void setGoogleAuthOtp(String googleAuthOtp) {
		this.googleAuthOtp = googleAuthOtp;
	}
	public String getGoogleAuthSecret() {
		return googleAuthSecret;
	}
	public void setGoogleAuthSecret(String googleAuthSecret) {
		this.googleAuthSecret = googleAuthSecret;
	}
	public String getGoogleAuthUser() {
		return googleAuthUser;
	}
	public void setGoogleAuthUser(String googleAuthUser) {
		this.googleAuthUser = googleAuthUser;
	}
	public String getGoogleAuthHost() {
		return googleAuthHost;
	}
	public void setGoogleAuthHost(String googleAuthHost) {
		this.googleAuthHost = googleAuthHost;
	}
	public String getGoogleAuthFormat() {
		return googleAuthFormat;
	}
	public void setGoogleAuthFormat(String googleAuthFormat) {
		this.googleAuthFormat = googleAuthFormat;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
			
}
