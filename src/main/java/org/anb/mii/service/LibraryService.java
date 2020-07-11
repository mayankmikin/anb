package org.anb.mii.service;

import java.util.List;

import org.anb.mii.model.Library;

public interface LibraryService 
{

	Library findByName(String name);
	List<Library> findNearestMatchingName(String name);

}
