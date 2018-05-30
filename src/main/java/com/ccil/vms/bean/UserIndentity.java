
package com.ccil.vms.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
@Scope("session")
public class UserIndentity  {
	
	private String email;

	
	@NotEmpty
	@NotNull
	@Email
	@Pattern(regexp=".+@.+\\.[a-z]+")
	public String getEmail() {
		return email;
	}
	
	

	public void setEmail(String email) {
		this.email = email;
	}



	
}
