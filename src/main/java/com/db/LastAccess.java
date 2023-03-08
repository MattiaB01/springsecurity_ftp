package com.db;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class LastAccess {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String username;
	
	private LocalDateTime date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LastAccess(Long id, String username, LocalDateTime date) {
		super();
		this.id = id;
		this.username = username;
		this.date = date;
	}

	public LastAccess() {
		super();
	}
	
	public LastAccess(String username, LocalDateTime date) {
		super();
		this.username = username;
		this.date = date;
	}
	
	
}
