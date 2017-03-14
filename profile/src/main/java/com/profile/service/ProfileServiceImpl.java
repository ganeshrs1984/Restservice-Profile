package com.profile.service;


import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.profile.config.UnmarshellExample;
import com.profile.dao.ProfileMapper;
import com.profile.exception.ProfileError;
import com.profile.exception.ProfileException;
import com.profile.helper.CommonUtil;
import com.profile.messages.Messages;
import com.profile.model.Profile;
import com.profile.model.ProfileContact;
import com.profile.response.APIRepsonse;
import com.profile.webservice.WebServiceTemplate;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService{

    @Autowired
	ProfileMapper profileMapper;
    
    @Autowired
    Messages message;

	/*@Autowired 
    APIRepsonse apiResponse;*/
    
    @Qualifier("profileWebServiceTemplate")
    @Autowired
    WebServiceTemplate profileWebServiceTemplate;
    
    @Qualifier("xmlBean")
    @Autowired
    UnmarshellExample helloXml;

    //the qualifier should be bean ID
    @Qualifier("jsonBean")
    @Autowired
    UnmarshellExample helloJson;
    
	public APIRepsonse getProfileDetails(String phone){
		APIRepsonse apiResponse = new APIRepsonse();
		if(!CommonUtil.isPhoneNumberValid(phone)){
		   try{
		        Profile profileDet = profileMapper.getProfileById(String.valueOf(phone));
		        if(profileDet != null){
		        	 apiResponse.setPayload(profileDet);
				     return apiResponse;	    	 
		        }else{
		        	throw(new ProfileException(new ProfileError("ACCOUNT_NOT_FOUND", phone +" :"+ message.getMessage("com.profile.accountNotExist"))));
		        }
		    }catch(Exception e){		    	 
		    	if(e instanceof ProfileException){
		    		throw e;
		    	}else {
		    		System.out.println(e);
		    		throw(new ProfileException(new ProfileError("TECHNICAL_DIFFICULTY", message.getMessage("com.proile.technicalDifficulty"))));
		    		}
		    	}
		   }else{
   	    	throw(new ProfileException(new ProfileError("INVALID_PHONE",message.getMessage("com.profile.invalidPhone"))));
   	    }
		
	}
	
	public int insertProfile(Profile profile){	
		try{
			System.out.println(1);
			profileMapper.insertProfile(profile.getPhone(),profile.getName(),profile.getEmail(),profile.getExp(),profile.getTech());      
		    return 0;
		}catch(DuplicateKeyException e){
			System.out.println(2);
			throw(new ProfileException(new ProfileError("DUPLICATE", profile.getPhone() +" :"+message.getMessage("com.profile.accountAlreadyExist"))));
		}catch(Exception e){
			throw(new ProfileException(new ProfileError("TECHNICAL_DIFFICULTY", message.getMessage("com.proile.technicalDifficulty"))));
		}
		
	}
	
    public ProfileMapper getProfileMapper() {
		return profileMapper;
	}

	public void setProfileMapper(ProfileMapper profileMapper) {
		this.profileMapper = profileMapper;
	}
	public Messages getMessage() {
		return message;
	}

	public void setMessage(Messages message) {
		this.message = message;
	}
 
	@Override
	public ProfileContact getProfileContactBO() {
		 ClassLoader classLoader = getClass().getClassLoader();
		 File file = new File(classLoader.getResource("InputXml/input.xml").getFile());
		 helloXml.printMessage();
		 helloJson.printMessage();
		return  profileWebServiceTemplate.callWebservice(file);
	}
	
}
