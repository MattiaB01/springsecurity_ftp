package com;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class config extends WebSecurityConfigurerAdapter {
	String[] staticResources = {
            "/styles/**",
           };
	// Adding the roles
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password("admin")
				.roles("admin_role")
				.and()
				.withUser("user")
				.password("user")
				.roles("student");
	}

	// Configuring the api
	// according to the roles.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
				httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers("/delete").hasRole("admin_role")
				.antMatchers("/details").hasAnyRole("admin_role","student")
				.antMatchers(staticResources).permitAll()
	        	.anyRequest().authenticated()  
				.and()
		        .formLogin()
		        	.loginPage("/index").permitAll()
		        	.and()  
		            .logout()  	
		            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index")
		            .permitAll();
		http.requiresChannel()
	      .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
	      .requiresSecure();
	}

	// Function to encode the password
	// assign to the particular roles.
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
