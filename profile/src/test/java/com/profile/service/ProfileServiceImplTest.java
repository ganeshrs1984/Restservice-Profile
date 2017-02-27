package com.profile.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import com.profile.dao.ProfileMapper;
import com.profile.exception.ProfileException;
import com.profile.messages.Messages;
import com.profile.model.Profile;
import com.profile.response.APIRepsonse;


public class ProfileServiceImplTest {
	
	ProfileServiceImpl profileservice;
	ProfileMapper profileMapper; 
	Messages message;
	
	@Before
	public void initate(){
		profileservice = new ProfileServiceImpl();
		profileMapper = mock(ProfileMapper.class);
		profileservice.setProfileMapper(profileMapper);
		message = mock(Messages.class);
		profileservice.setMessage(message);
	}
	 
    @Test
	public void getProfileDetails_Valid_PhoneNumber(){
		 Profile profileDet = new Profile();
		 profileDet.setEmail("test1@gmail.com");
		 profileDet.setExp(10);
		 profileDet.setName("Ganesh");
		 profileDet.setTech("Java");
		 profileDet.setPhone("6111111111");
		 when(profileMapper.getProfileById("1234567890")).thenReturn(profileDet);
		 APIRepsonse apiReponse = profileservice.getProfileDetails("1234567890");
		 assertEquals("test1@gmail.com"
		 		+ "", apiReponse.getProfileDet().getEmail());
	}
	
	 
    @Test
	public void getProfileDetails_In_Valid_PhoneNumber(){
		try{
	     when(message.getMessage("com.profile.invalidPhone")).thenReturn("Given PhoneNumber is invalid");
		 APIRepsonse apiReponse = profileservice.getProfileDetails("123456789A"); 
		 fail("Fail here");
		}catch(Exception e){
			 assertNotNull(e);
		     assertEquals("INVALID_PHONE", ((ProfileException) e).getProfileError().getErrorId());
		     assertEquals("Given PhoneNumber is invalid", ((ProfileException) e).getProfileError().getErrorMessage());
		}
	}
	
 
    @Test
	public void getProfileDetails_PhoneNumber_NOT_FOUND(){
		try{
		  Profile profileDet = null;  
	     when(profileMapper.getProfileById("1234567890")).thenReturn(profileDet);
	     when(message.getMessage("com.profile.accountNotExist")).thenReturn("Account does not exist");
		 APIRepsonse apiReponse = profileservice.getProfileDetails("1234567890"); 
		 fail("Fail here");
		}catch(Exception e){
			 assertNotNull(e);
		     assertEquals("ACCOUNT_NOT_FOUND", ((ProfileException) e).getProfileError().getErrorId());
		     assertEquals("1234567890 :Account does not exist", ((ProfileException) e).getProfileError().getErrorMessage());
		}
	}
	
	@Test
	public void getProfileDetails_THROW_UNHANDLED_EXCEPTION(){
	     try{
	     doThrow(mock(DuplicateKeyException.class)).when(profileMapper).getProfileById("1234567890");	 
	     when(message.getMessage("com.proile.technicalDifficulty")).thenReturn("Technical difficulty : Sorry for the inconvenience, please try again latter");	 
	     APIRepsonse apiReponse = profileservice.getProfileDetails("1234567890"); 
		 fail("Fail here");
		}catch(Exception e){
			 System.out.println("test1");
			 assertNotNull(e);
		  //   assertEquals("TECHNICAL_DIFFICULTY", ((ProfileException) e).getProfileError().getErrorId());
		   //  assertEquals("Technical difficulty : Sorry for the inconvenience, please try again latter", ((ProfileException) e).getProfileError().getErrorMessage());
		}
	}

	@Test 
	public void insertProfile_INSERT_PROILE_DETAILS(){
		Profile profileDet = new Profile();
		 profileDet.setEmail("test1@gmail.com");
		 profileDet.setExp(10);
		 profileDet.setName("Ganesh");
		 profileDet.setTech("Java");
		 profileDet.setPhone("6111111111");
		when(profileMapper.insertProfile("1234567890","test1", "test1@gmail.com", 10,"Java,Groovy")).thenReturn(0);
		int result = profileservice.insertProfile(profileDet);
		assertEquals(0, result);
		
	}
	
	@Test
	public void insertProfile_INSERT_DUPLICATE(){
		 try {
			 Profile profileDet = new Profile();
			 profileDet.setEmail("test1@gmail.com");
			 profileDet.setExp(10);
			 profileDet.setName("Ganesh");
			 profileDet.setTech("Java");
			 profileDet.setPhone("6111111111");
			 when(message.getMessage("com.profile.accountAlreadyExist")).thenReturn("Account already exist");
			 doThrow(mock(DuplicateKeyException.class)).when(profileMapper).insertProfile("6111111111","Ganesh", "test1@gmail.com", 10,"Java");
			  profileservice.insertProfile(profileDet);
		      fail("shouldn't go here");
		    } catch (Exception e) {
		      assertNotNull(e);
		      assertEquals("DUPLICATE", ((ProfileException) e).getProfileError().getErrorId());
		      assertEquals("6111111111 :Account already exist", ((ProfileException) e).getProfileError().getErrorMessage());
		    }
		
	}
	
	@Test
	public void insertProfile_INSERT_UNHANDLED(){
		 try {
			 Profile profileDet = new Profile();
			 profileDet.setEmail("test1@gmail.com");
			 profileDet.setExp(10);
			 profileDet.setName("Ganesh");
			 profileDet.setTech("Java");
			 profileDet.setPhone("6111111111");
			 when(message.getMessage("com.proile.technicalDifficulty")).thenReturn("Technical difficulty : Sorry for the inconvenience, please try again latter");
			 doThrow(mock(ProfileException.class)).when(profileMapper).insertProfile("6111111111","Ganesh", "test1@gmail.com", 10,"Java");
			 profileservice.insertProfile(profileDet);
		     fail("shouldn't go here");
		    } catch (Exception e) {
		      assertNotNull(e);
		      assertEquals("TECHNICAL_DIFFICULTY", ((ProfileException) e).getProfileError().getErrorId());
		      assertEquals("Technical difficulty : Sorry for the inconvenience, please try again latter", ((ProfileException) e).getProfileError().getErrorMessage());
		    }
		
	}
}
