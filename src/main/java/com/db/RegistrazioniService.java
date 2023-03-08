package com.db;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


	@Repository
	public interface RegistrazioniService extends CrudRepository<Registrazioni, Long> {
	
		
		 @Query("FROM Registrazioni u WHERE u.uuid = ?1 ")
		 Registrazioni findByName(String uuid);

	
}
