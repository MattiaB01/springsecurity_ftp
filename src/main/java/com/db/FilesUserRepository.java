/*
package com.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FilesUserRepository extends CrudRepository<FilesUser,Long>  {

	
	@Query("FROM FilesUser u WHERE u.nomefile = ?1 ")
	FilesUser findByNomefile(String nomefile);

}


*/