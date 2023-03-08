package com.users;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table (name="users")
public class Users implements UserDetailsService {
	
		@Id @Column(length = 50, nullable = false) 
		//@GeneratedValue(strategy = GenerationType.IDENTITY)
		@JoinColumn(nullable=false)
		private String username;

		@OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
		@JoinColumn(nullable=false)
	    private Authorities authorities;
		
		/*private String lastLogin;
		
		public String getLastLogin() {
			return lastLogin;
		}

		public void setLastLogin(String lastLogin) {
			this.lastLogin = lastLogin;
		}*/

		public Authorities getAuthorities() {
			return authorities;
		}

		public void setAuthorities(Authorities authorities) {
			this.authorities = authorities;
		}

		public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
		@Column @NonNull
		private String password;
		
		private boolean enabled;
		
		public Users() {};

		public Users(String username, String pw) {
			super();
			 if (username != null && username.isEmpty())
				    this.username = null;
				  else
				    this.username = username;
	
			this.username = username;
			this.password = pw;
			
		}
		
		public Users(String username, String pw, boolean enabled) {
			super();
			 if (username != null && username.isEmpty())
				    this.username = null;
				  else
				    this.username = username;
			this.username = username;
	
			this.password = pw;
			this.enabled = enabled;
			
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			  if (username != null && username.isEmpty())
				    this.username = null;
				  else
				    this.username = username;
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String pw) {
			try {
			if (pw.isEmpty()) password="";
			else {
			BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
			this.password =  enc.encode(pw);
			}}
			catch (Exception e) 
			{
				password=null;
			}
		}
		

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}

		


		
		
		

}


