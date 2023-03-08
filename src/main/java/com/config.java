package com;



import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import com.users.CustomAuthenticationProvider;
import com.users.CustomUserDetailsService;
import com.users.Users;

@Configuration
@EnableWebSecurity
public class config {
	String[] staticResources = {
            "/styles/**",
           };
	
	
	@Autowired
    DataSource dataSource;
	
	
	@Bean
	public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
	
			
	@Bean // per usare entrambi inMemory and Database Login
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
          http.getSharedObject(AuthenticationManagerBuilder.class);
        //authenticationManagerBuilder.authenticationProvider(customAuthProvider);
        authenticationManagerBuilder.inMemoryAuthentication()
            .withUser("user")
            .password(passwordEncoder().encode("user"))
            .roles("USER");
        authenticationManagerBuilder.inMemoryAuthentication()
        .withUser("admin")
        .password(passwordEncoder().encode("admin"))
        .roles("ADMIN");
	   authenticationManagerBuilder.jdbcAuthentication()
	      .dataSource(dataSource);
	     
        return authenticationManagerBuilder.build();
    }

	
	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
    */
	
/*
	
	
	 
	
	
	//Bean per la gestione dei cookies
/*
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}*/
	
	
   
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,AuthenticationManager authManager) throws Exception {
	
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/index").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				//.antMatchers("/registra").permitAll()
				.antMatchers("/adduser").permitAll()
				.antMatchers("/react").permitAll()
				.antMatchers(staticResources).permitAll()
				//.antMatchers("/upload").hasRole("ADMIN")
			    //	.antMatchers("/upload").permitAll()
				//.antMatchers("/lista").hasRole("ADMIN")
				//.antMatchers("/lista").permitAll()
				//.antMatchers("/account").permitAll()
			
				.antMatchers("/uuid").hasRole("ADMIN")
				//.antMatchers("/upload").hasAuthority("ROLE_ADMIN")
				.antMatchers("/listautenti").hasRole("ADMIN")
				
				.anyRequest().authenticated()
				
				.and()
			
	            .authenticationManager(authManager)
	           				
			)
			  
			 
		
			.formLogin((form) -> form
				.loginPage("/index")
				.permitAll()
				//.failureHandler((request, response, exception) -> System.out.println("aaa"+exception))
			)
		
			.logout((logout) -> logout
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index")
        .permitAll()
        );
	
		return http.build();
	}





	


	   @Bean
		public PasswordEncoder passwordEncoder() {
		   System.out.println(new BCryptPasswordEncoder().encode("mattia"));
		   System.out.println(new BCryptPasswordEncoder().encode("utente"));
		    return new BCryptPasswordEncoder();
		    
		}
	
	   
}