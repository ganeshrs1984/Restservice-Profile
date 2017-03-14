package com.profile.webservice;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.profile.converters.Unmarsheller;
import com.profile.converters.XmlUnmarshellersImpl;
import com.profile.model.ProfileContact;

@Service
public class WebServiceTemplate {

	Unmarsheller unmarsheller;
	
	public WebServiceTemplate(Unmarsheller unmarsheller) {
		this.unmarsheller = unmarsheller;
	}
	
	public ProfileContact callWebservice(File file){
/*		Unmarsheller xmlConverters = new XmlConvertersImpl();*/
		return unmarsheller.convertXMLtoOBJ(file);
	
	}
}
