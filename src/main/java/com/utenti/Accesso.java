package com.utenti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table (name="Accesso")
public class Accesso {
	public Accesso() {
		
	}
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	*/
	 @Id @Column(length = 50)
	private String username;
	
	private String pw;

	public Accesso(String username, String pw) {
		super();
		this.username = username;
		this.pw = pw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		this.pw =  enc.encode(pw);
	}
	
	

}
