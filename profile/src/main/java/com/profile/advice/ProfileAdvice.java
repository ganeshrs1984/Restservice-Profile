package com.profile.advice;

import java.util.Locale;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.profile.exception.ProfileError;
import com.profile.exception.ProfileException;

public class ProfileAdvice {

	  @ExceptionHandler
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public ProfileError handleProductException(ProfileException exp) {
	    return exp.getProfileError();
	  }
	
	
}
