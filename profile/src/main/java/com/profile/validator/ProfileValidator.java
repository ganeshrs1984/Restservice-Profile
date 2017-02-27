package com.profile.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.profile.exception.ProfileError;
import com.profile.exception.ProfileException;
import com.profile.helper.CommonUtil;
import com.profile.messages.Messages;
import com.profile.model.Profile;


 
@Component
public class ProfileValidator implements Validator{
	
	 @Autowired
	 Messages messages;
	 
	  public void validate(Object target, Errors errors) {
	    Profile profile = (Profile) target;
	    if (CommonUtil.isPhoneNumberValid(profile.getPhone())) {	 
	      errors.reject("Invalid Phone");
	      throw new ProfileException(new ProfileError("INVALID_PHONE", messages.getMessage("com.profile.invalidPhone")));
	    } else if (profile.getEmail() == null || !CommonUtil.isValidEmailAddress(profile.getEmail())) {
	      errors.reject("Invalid Email ID");
	      throw new ProfileException(new ProfileError("INVALID_EMAIL", messages.getMessage("com.profile.emailInvalid")));
	    }else if (CommonUtil.isNameValid(profile.getName())) {
		      errors.reject("Invalid Name");
		      throw new ProfileException(new ProfileError("INVALID_NAME", messages.getMessage("com.profile.nameInvalid")));
		 }else if(CommonUtil.isTechValid(profile.getTech())){
			 errors.reject("Invalid Tech");
		      throw new ProfileException(new ProfileError("INVALID_TECH", messages.getMessage("com.profile.techInvalid")));
		 }else if(CommonUtil.isExpValid(profile.getExp())){
			 errors.reject("Invalid Exp");
		      throw new ProfileException(new ProfileError("INVALID_EXPERIENCE", messages.getMessage("com.profile.expInvalid")));
		 }
	  }

	@Override
	public boolean supports(Class<?> clazz) {
		return Profile.class.isAssignableFrom(clazz);
	}

}
