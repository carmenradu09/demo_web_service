package org.capgemini.carmen.demo.webservice.repo;

import org.capgemini.carmen.demo.webservice.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByName(String username);
	List<User> findByEmail(String email);
}
