package com.db;


import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "registrazioni")
public class Registrazioni {
 
		@Id
	    @Column(name = "id")
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	     
	    @Column(name = "uuid", length = 128, nullable = false, unique = true)
	    private String uuid;
	    
	    @Column(name = "email",  length = 128, nullable = false, unique = false)
	    private String email;
	    
	     
	    @Column(name = "password",  length = 128, nullable = false, unique = false)
	    private String password;
	     
	    @Column(name ="scadenza")
	    private LocalDate date;
	    
	    @Column(name="attivo")
	    private int attivo;

		public int getAttivo() {
			return attivo;
		}

		public void setAttivo(int attivo) {
			this.attivo = attivo;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id =id;
		}

		public String getName() {
			return uuid;
		}

		public void setName(String uuid) {
			this.uuid = uuid;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public Registrazioni(Long id, String uuid, String email, String password, LocalDate date, int attivo) {
			super();
			this.id = id;
			this.uuid = uuid;
			this.email = email;
			this.password = password;
			this.date = date;
			this.attivo=attivo;
		}
		
		public Registrazioni() {
			super();
		}
	    

}
