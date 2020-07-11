package org.anb.mii.repository;

import java.util.List;

import org.anb.mii.model.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

// jpa interacts with DB
// by default one server blocks calls from another server , so i used crossorigin
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RepositoryRestResource(collectionResourceRel = "library", path = "library")
public interface LibraryRepository extends CrudRepository<Library, Long> {

	List<Library> findByName(String name);
}
