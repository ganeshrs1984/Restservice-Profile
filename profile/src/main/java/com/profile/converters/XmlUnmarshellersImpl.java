package com.profile.converters;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.profile.model.ProfileContact;

public class XmlUnmarshellersImpl implements Unmarsheller{
 
	
	
	public ProfileContact convertXMLtoOBJ(File file){
		JAXBContext jaxbContext = null;
		Unmarshaller jaxbUnmarshaller;
		ProfileContact profileContact = null;
		try {
			jaxbContext = JAXBContext.newInstance(ProfileContact.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			profileContact = (ProfileContact) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(profileContact);
		return profileContact;		
	}
}
