package com.ccil.vms.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ccil.vms.model.UserIdentity;
import com.ccil.vms.service.VendorService;

@Component
public class UserIdentityValidator implements Validator {

	@Autowired
	private VendorService vendorService;

	@Override
	public boolean supports(Class<?> aClass) {
		return UserIdentity.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		UserIdentity userIndentity = (UserIdentity) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if (!(pattern.matcher(userIndentity.getEmail()).matches())) {
			errors.rejectValue("email", "userIdentity.email.invalid");
		}
		if (vendorService.findByVendorDomain(userIndentity.getEmail().substring(userIndentity.getEmail().indexOf('@') + 1)) == null) {
			errors.rejectValue("email", "vendorDomain.email.invalid");
		}

	}

}
