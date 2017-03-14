package com.profile.service;

import java.io.File;

import com.profile.model.Profile;
import com.profile.model.ProfileContact;
import com.profile.response.APIRepsonse;



public interface ProfileService {

	public APIRepsonse getProfileDetails(String phone);

	public int insertProfile(Profile profile);
	
	public ProfileContact getProfileContactBO();
	
}
