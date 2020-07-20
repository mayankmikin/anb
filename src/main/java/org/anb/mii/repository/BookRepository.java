package org.anb.mii.repository;

import org.anb.mii.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
// jpa interacts with DB
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RepositoryRestResource(collectionResourceRel = "book", path = "book")
public interface BookRepository extends CrudRepository<Book, Long> {

	//List<Book> findAllById(List<Long> ids);
	
}
