package com.profile.service;

import com.profile.model.Profile;
import com.profile.response.APIRepsonse;



public interface ProfileService {

	public APIRepsonse getProfileDetails(String phone);

	public int insertProfile(Profile profile);
	
}
