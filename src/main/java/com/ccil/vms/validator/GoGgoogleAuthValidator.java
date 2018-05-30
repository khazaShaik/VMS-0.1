package com.ccil.vms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ccil.vms.exceptions.VerificationCodeException;
import com.ccil.vms.model.GoogleAuth;
import com.ccil.vms.util.TimeBasedOneTimePassword;

@Component
public class GoogleAuthValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> aClass) {
		return GoogleAuth.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		GoogleAuth googleAuth = (GoogleAuth) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "googleAuthOtp", "NotEmpty");
		try {
			if (!TimeBasedOneTimePassword.isVerificationCodeValid(Integer.parseInt(googleAuth.getGoogleAuthOtp()),googleAuth.getGoogleAuthSecret())){
				errors.rejectValue("googleAuthOtp", "googleAuth.googleAuthOtp.invalid");
			}
		} catch (NumberFormatException | VerificationCodeException e) {
			errors.rejectValue("googleAuthOtp", "googleAuth.googleAuthOtp.error");
		}	
		
	}

}
