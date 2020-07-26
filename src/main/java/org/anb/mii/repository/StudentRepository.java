package org.anb.mii.repository;

import org.anb.mii.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
// jpa interacts with DB
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RepositoryRestResource(collectionResourceRel = "student", path = "student")
public interface StudentRepository extends CrudRepository<Student, Long> {

}
