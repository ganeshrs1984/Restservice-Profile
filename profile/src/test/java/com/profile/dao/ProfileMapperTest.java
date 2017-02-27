package com.profile.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.profile.model.Profile;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProfileMapperTest {

	 @Autowired
	  ProfileMapper profileMapper;

	  @Test
	  public void getProfile() {
	    Profile profile = profileMapper.getProfileById(String.valueOf("6121111111"));
	    assertNotNull("profile Available", profile);
	    assertEquals(10, profile.getExp());
	  }
	  
	 @Test
	  public void getProfile_Not_Available(){
	    Profile profile = profileMapper.getProfileById(String.valueOf("1234567891"));
	    assertNull("Profile not available", profile);
	  }
	 
	 @Test
	  public void insertPrice() {
	    int success = profileMapper.insertProfile("1234567890", "testN","test1@gmail.com", 12, "Java");
	    assertEquals("Inserted Successfully", 1, success);
	    Profile profile = profileMapper.getProfileById("1234567890");
	    assertNotNull("profile Available", profile);
	    assertEquals(12, profile.getExp());
	  }
	
}
