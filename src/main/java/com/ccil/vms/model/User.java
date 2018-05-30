package com.ccil.vms.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import com.ccil.vms.bean.UserIndentity;
@Entity
@Table(name = "VMS_USER_MST")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long	id;
	private String	username;
	private String	password;
	private String	passwordConfirm;
	private String	email;
	private String	loginStatus	= "N";
	private int		loginAtempt;
	private String	lockStatus	= "N";
	private String	userExpire	= "N";
	private GoogleAuth googleAuth;
	private Employee employee;

		
	@OneToOne(targetEntity = Employee.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeID", referencedColumnName = "id")
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(UserIndentity userIdentity) {
		email = userIdentity.getEmail();
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public int getLoginAtempt() {
		return loginAtempt;
	}
	public void setLoginAtempt(int loginAtempt) {
		this.loginAtempt = loginAtempt;
	}
	public String getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	public String getUserExpire() {
		return userExpire;
	}
	public void setUserExpire(String userExpire) {
		this.userExpire = userExpire;
	}
	private List<Role> roles;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
	
	
	@OneToOne(targetEntity = GoogleAuth.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "googleId", referencedColumnName = "id")
	public GoogleAuth getGoogleAuth() {
		return googleAuth;
	}
	public void setGoogleAuth(GoogleAuth googleAuth) {
		this.googleAuth = googleAuth;
	}
}
