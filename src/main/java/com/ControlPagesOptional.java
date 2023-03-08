package com;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.LastAccess;
import com.db.LastAccessRepository;
import com.storage.StorageProperties;
import com.storage.StorageService;
import com.users.Authorities;
import com.users.AuthoritiesRepository;
import com.users.UsersRepository;


@Controller
public class ControlPagesOptional {

	
	@Autowired
	StorageProperties st;
	
    @Autowired
    AuthoritiesRepository ar;
    @Autowired
    UsersRepository ur;
       
	@Autowired
	private LastAccessRepository access;
	
	@RequestMapping(value = "/currentuser", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        
		LastAccess current= new LastAccess(authentication.getName(),LocalDateTime.now());
		
		if(access.findByUsername(authentication.getName())!=null)
		{
			LastAccess other= access.findByUsername(authentication.getName());	
			other.setDate(LocalDateTime.now());
			access.save(other);
		
		}
		else {
			access.save(current);
		}
		
		
		return authentication.getName();
        
     
        
    }
	
	
	
	@GetMapping("/deletecurrent")
	//@ResponseBody
	public String deleteCurrent(Authentication auth)
	{
		
		try {
			
		//nell'eliminare l'account viene eliminata la directory
		
		String loc = st.getLocation();
		FileUtils.deleteDirectory(new File(loc+"/"+auth.getName()));
			
		ar.delete(ar.findByUsername(auth.getName()));
		ur.delete(ur.findByUsername(auth.getName()));
		//return "Utente cancellato";
		return "redirect:/logout";
		}
		catch (Exception e)
		{
			return ""+e;
		}
		
	}
	
	@GetMapping("/ip")
	@ResponseBody
	public String ip(HttpServletRequest request)
	{
	return	request.getRemoteAddr();
	}
	
}
