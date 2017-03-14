package com.profile.converters;

import java.io.File;

import com.profile.model.ProfileContact;

public interface Unmarsheller {
	
	public ProfileContact convertXMLtoOBJ(File file);

}
