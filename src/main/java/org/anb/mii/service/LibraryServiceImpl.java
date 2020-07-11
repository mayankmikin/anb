package org.anb.mii.service;

import java.util.ArrayList;
import java.util.List;

import org.anb.mii.model.Library;
import org.anb.mii.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRepository libRepo;
	
	@Override
	public Library findByName(String name) {
		
		List<Library>librarylist= libRepo.findByName(name);
		for(Library lib :librarylist)
		{
			 if(lib.getName().equalsIgnoreCase(name))
			 {
				 return lib;
			 }
		}
		
		return null;
	}

	@Override
	public List<Library> findNearestMatchingName(String name) {
		List<Library>nearestMatchingResult = new ArrayList<Library>();
		
		List<Library>librarylist= (List<Library>) libRepo.findAll();
		for(Library lib :librarylist)
		{
			 if(lib.getName().contains(name))
			 {
				 nearestMatchingResult.add(lib);
			 }
		}
		return nearestMatchingResult;
	}

}
