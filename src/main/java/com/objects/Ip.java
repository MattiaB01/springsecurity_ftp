package com.objects;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class Ip {

	private String ip;
	
	public String getIp() {
	return ip;
	}
	
	public void setIp() {
		WebAuthenticationDetails details = (WebAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
		String ip = details.getRemoteAddress();
		this.ip=ip;
	}
	

}
