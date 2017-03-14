package com.profile.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.profile.messages.Messages;
import com.profile.model.Profile;
import com.profile.model.ProfileContact;
import com.profile.response.APIRepsonse;
import com.profile.service.ProfileService;
import com.profile.validator.ProfileValidator;

//@Configuration
/*@ComponentScan(basePackages =  { "com.profile.*" })*/
@RestController
public class ProfileController {

 	@Autowired 
	ProfileService profileService; 
 	
 	
 	@Autowired 

 	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	public ProfileValidator getProfileValidator() {
		return profileValidator;
	}

	public void setProfileValidator(ProfileValidator profileValidator) {
		this.profileValidator = profileValidator;
	}

	@Autowired
 	ProfileValidator profileValidator;
	 
 	@InitBinder("profile")
 	  public void setProductsBinder(WebDataBinder webDataBinder) {
 	    webDataBinder.setValidator(profileValidator);
 	  }
 	
 	@Autowired
 	Messages message;
 
 	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/profile/{phoneNum}",method=RequestMethod.GET)
    public @ResponseBody  APIRepsonse getDetailsByPhonenumber1(@PathVariable String phoneNum) { 
        return profileService.getProfileDetails(phoneNum);
    }
	
	 @ResponseStatus(value = HttpStatus.OK)
	 @RequestMapping(value = "/insert", method = RequestMethod.POST)
	 public Profile insert(@RequestBody @Valid Profile profile) {
		profileService.insertProfile(profile);
	    return profile;
	 }

	 @ResponseStatus(value = HttpStatus.OK)
	 @RequestMapping(value = "/update", method = RequestMethod.POST)
	 public Profile updatProfile(@RequestBody @Valid Profile profile) {
		profileService.insertProfile(profile);
	    return profile;
	 }
	 
	 @ResponseStatus(value = HttpStatus.OK)
	 @RequestMapping(value = "/profileContact/{phoneNum}", method = RequestMethod.GET)
	 public ProfileContact profileContact(@PathVariable String phoneNum) {
		 return  profileService.getProfileContactBO();
	 }
	 
	 

}
