package com.profile.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profile.model.Profile;
import com.profile.response.APIRepsonse;
import com.profile.service.ProfileService;
 

public class ProfileControllerTest {
	
	 @Autowired
	  private ProfileController profileController;

	  private ProfileService profileService;

	  private MockMvc mockMvc;

	  String profileJson;
	  ObjectMapper objMapper = new ObjectMapper();
	  
	  @Before
	  public void setup() {
		  Profile profileDet = new Profile();
			 profileDet.setEmail("test1@gmail.com");
			 profileDet.setExp(10);
			 profileDet.setName("Ganesh");
			 profileDet.setTech("Java");
			 profileDet.setPhone("6111111129");
	    profileService = mock(ProfileService.class);
	    profileController = new ProfileController();
	    profileController.setProfileService(profileService);
	    mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
	    try {
	      profileJson = objMapper.writeValueAsString(profileDet);
	    } catch (JsonProcessingException e) {
	       System.out.println(e);
	    }
	  }
	  
	  @Test
	  public void getProfile() {
	    try {
	    	  Profile profileDet = new Profile();
				 profileDet.setEmail("test1@gmail.com");
				 profileDet.setExp(10);
				 profileDet.setName("Ganesh");
				 profileDet.setTech("Java");
				 profileDet.setPhone("6111111111");	
	      APIRepsonse apiRepsonse = new APIRepsonse();
	      apiRepsonse.setPayload(profileDet);
	      when(profileService.getProfileDetails("6121111111")).thenReturn(apiRepsonse);
	      mockMvc.perform(get("/profile/6121111111").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
	          andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	    } catch (Exception e) {
	       
	    }
	  }

 	  
	  @Test
	  public void insertProfile() {
	    try {
	      mockMvc.perform(post("/insert").contentType(MediaType.APPLICATION_JSON).content(profileJson)).andExpect(status().isOk()).
	          andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	    } catch (Exception e) {
	       
	    }
	  }
	  
}
