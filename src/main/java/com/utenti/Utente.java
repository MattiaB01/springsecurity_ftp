package com.utenti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;





@Entity
@Table (name="Utente")
public class Utente {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="name")
	private String name;
    
	@Column(name="email")
    private String email;

	public Utente(Long id,String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Utente()
	{
		
	}



    // standard constructors / setters / getters / toString
}