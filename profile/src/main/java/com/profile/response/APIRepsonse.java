package com.profile.response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.profile.model.Profile;

@Component
@Scope(scopeName="prototype")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class APIRepsonse {
	
	private static final int status = 200;
	
	public int getStatus() {
		return status;
	}

	@JsonProperty(value = "profile")
	Profile profileDet;
	
	
	
	public Profile getProfileDet() {
		return profileDet;
	}

	public void setPayload(Profile payload) {
		this.profileDet = payload;
	}

	
}
