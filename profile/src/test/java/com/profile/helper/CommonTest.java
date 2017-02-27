package com.profile.helper;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommonTest {
	
	@Test
	public void ValidEmailAddress_Test(){
		assertEquals(true,CommonUtil.isValidEmailAddress("test1@gmail.com"));
	}
	
	@Test
	public void ValidPhoneNumber_Test(){
		assertEquals(false,CommonUtil.isPhoneNumberValid("1234567890"));
	}

	@Test
	public void ValidExp_Test(){
		assertEquals(false,CommonUtil.isExpValid(10));
	}
	
	@Test
	public void ValidTech_Test(){
		assertEquals(false,CommonUtil.isTechValid("Java"));
	}
	
	@Test
	public void ValidName_Test(){
		assertEquals(false,CommonUtil.isNameValid("Ganesh"));
	}
	
	@Test
	public void IN_ValidEmailAddress_Test(){
		assertEquals(false,CommonUtil.isValidEmailAddress("test1gmail.com"));
	}
	
	@Test
	public void IN_ValidPhoneNumber_Test(){
		assertEquals(true,CommonUtil.isPhoneNumberValid("123456789A"));
	}

	@Test
	public void IN_ValidExp_Test(){
		assertEquals(true,CommonUtil.isExpValid(0));
	}
	
	@Test
	public void IN_ValidTech_Test(){
		assertEquals(true,CommonUtil.isTechValid(null));
	}
	
	@Test
	public void IN_ValidName_Test(){
		assertEquals(true,CommonUtil.isNameValid(null));
	}
}
