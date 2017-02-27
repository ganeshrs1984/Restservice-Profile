package com.profile.messages;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {

	    @Autowired
	    private MessageSource messageSource;

	    public String getMessage(String id) {
	    	Locale locale = LocaleContextHolder.getLocale();
	        return messageSource.getMessage(id,null,locale);
	    }


}
