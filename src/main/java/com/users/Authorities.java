package com.users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table (name="authorities")
public class Authorities {

	@Id @Column (length=50,nullable = false)
	@JoinColumn(nullable=false)
	private String username;
	 
	 @OneToOne
     @PrimaryKeyJoinColumn
     @JoinColumn(nullable=false)
     private Users users;
	 
	 
	private String authority;
	
	
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Authorities(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}

	public Authorities() {
		super();
	}
	
	
}
