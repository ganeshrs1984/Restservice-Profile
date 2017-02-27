package com.profile.helper;

import java.util.regex.Pattern;

public class CommonUtil {
	
	 public static boolean isValidEmailAddress(String email) {
         String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
         java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
         java.util.regex.Matcher m = p.matcher(email);
         return m.matches();
      }

	  public static boolean isPhoneNumberValid(String phone){
//		  System.out.println(phone.matches(".*\\d+.*"));
		  Pattern pattern = Pattern.compile(".*[^0-9].*");
		  System.out.println(pattern.matcher(phone).matches());
         if(phone == null || phone.trim().length()< 10 || pattern.matcher(phone).matches()){
        	 System.out.println(phone.trim().length());
        	 return true;
         }
		    return false;	  
	  }
	  
	  public static boolean isNameValid(String name){
	         if(name == null || name.trim().length() == 0){
	        	 return true;
	         }
			    return false;	  
	   }
	  
	  public static boolean isTechValid(String tech){
	         if(tech == null || tech.trim().length() == 0){
	        	 return true;
	         }
			    return false;	  
	   }
	  
	  public static boolean isExpValid(int exp){
	         if(exp <= 0){
	        	 return true;
	         }
			    return false;	  
	   }
	  
}
