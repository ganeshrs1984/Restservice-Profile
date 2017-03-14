package com.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.profile.converters.Unmarsheller;
import com.profile.converters.XmlUnmarshellersImpl;
import com.profile.webservice.WebServiceTemplate;

@Configuration
public class Resources {

	@Bean(name="xmlBean")
    public UnmarshellExample helloXml() {
		System.out.println("I am here");
        return new XmlUnmarshellImpl();
    }
	
	@Bean(name="jsonBean")
    public UnmarshellExample helloJson() {
		System.out.println("I am here");
        return new JsonUnmarshellImpl();
    }
	
	@Bean(name="profileWebServiceTemplate")
	public WebServiceTemplate getProfileWebServiceTemplate(){
		return new WebServiceTemplate(getXmlUnmarshellObj());
	}
	
	@Bean
	public Unmarsheller getXmlUnmarshellObj(){
		return new XmlUnmarshellersImpl();
	}
}
