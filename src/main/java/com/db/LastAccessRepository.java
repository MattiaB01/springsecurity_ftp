package com.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LastAccessRepository extends CrudRepository<LastAccess,Long>  {

	
	@Query("FROM LastAccess u WHERE u.username = ?1 ")
	LastAccess findByUsername(String username);

}


