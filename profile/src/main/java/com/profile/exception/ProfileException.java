package com.profile.exception;

 
public class ProfileException extends RuntimeException {

    public ProfileException(ProfileError profileError) {
		    this.profileError = profileError;
    }
	private ProfileError profileError;

	public ProfileError getProfileError() {
		return profileError;
	}

	public void setProfileError(ProfileError profileError) {
		this.profileError = profileError;
	}

 
}
