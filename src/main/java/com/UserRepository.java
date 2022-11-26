package com;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
//	  @Query(" from user s where s.name like ?1")
//	    User findByName(String name);
	
	// per implementare una query personalizzata
	 @Query("FROM User u WHERE u.name = ?1 ")
	 List<User> findByName(String name);

}