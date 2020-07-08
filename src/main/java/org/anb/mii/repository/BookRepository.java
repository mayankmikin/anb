package org.anb.mii.repository;

import org.anb.mii.model.Book;
import org.springframework.data.repository.CrudRepository;
// jpa interacts with DB
public interface BookRepository extends CrudRepository<Book, Long> {

}
