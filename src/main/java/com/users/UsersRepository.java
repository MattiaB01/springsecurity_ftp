package com.users;


import org.springframework.data.repository.CrudRepository;



public interface UsersRepository extends CrudRepository<Users,Long>{
	 Users findByUsername(String username);
}