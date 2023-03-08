package com.utenti;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UtenteRepository extends CrudRepository<Utente, Long>{
	 @Query("FROM Utente u WHERE u.name = ?1 ")
	 Utente findByName(String name);

}
