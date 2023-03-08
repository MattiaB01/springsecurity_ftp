package com;





import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.db.FilesUser;
//import com.db.FilesUserRepository;
import com.db.Registrazioni;
import com.db.RegistrazioniService;
import com.function.Uuid;
import com.objects.Ip;
import com.storage.StorageFileNotFoundException;
import com.storage.StorageProperties;
import com.storage.StorageService;
import com.users.Authorities;
import com.users.AuthoritiesRepository;
import com.users.Users;
import com.users.UsersRepository;
import com.utenti.Accesso;
import com.utenti.AccessoRepository;
import com.utenti.Sessione;
import com.utenti.Utente;
import com.utenti.UtenteRepository;






@Controller
public class controllerPages {
	
	private final StorageService storageService;
	
	private StorageProperties storageProperties;

	private RegistrazioniService registrazioniService;
	
	private UtenteRepository ur2;

	@Autowired
	private AuthoritiesRepository autRep;
	
	@Autowired
	private UsersRepository ur_;
	
	@Autowired
	private AccessoRepository r;
	
	
	/*@Autowired
	private FilesUserRepository fr ;*/
	

	
	public controllerPages(UtenteRepository ur2,StorageService storageService, StorageProperties storageProperties,RegistrazioniService reg) {
		this.storageService = storageService;
		this.storageProperties=storageProperties;
		this.registrazioniService = reg;
		this.ur2=ur2;
	}

	@GetMapping("/uuid")
	@ResponseBody
	public String uuid() throws IOException
	{
		return Uuid.getUuid();
	}

	
	
	@GetMapping("/")
	public String main(Model model,Users user,HttpServletRequest request)
	{
	   Ip ip = new Ip();
	   ip.setIp();
	   
	   model.addAttribute("ip",ip);
	/*
	   user.setLastLogin("AAA");
	   ur_.save(user);*/
	
	   
	//   return "main";
	   return "redirect:/lista";
	}
	
	
	@GetMapping("/listautenti")
	public String a(Model model)
	{
		model.addAttribute("users", ur_.findAll());
		return "list-user";
		
	}
	
	
	   @GetMapping("/deleteuser/{username}")
	    public String deleteUser(@PathVariable("username") String username, Model model) {
		   Users user = ur_.findByUsername(username);
	          //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	        ur_.delete(user);
	        return "redirect:/index";
	    }
	
	   
	@GetMapping("/react")
	@ResponseBody
	public String react()
	{
		return "prova react";
	}
	   

	@GetMapping("/error")
	public String error()
	{
		return "error";
	}
	
	@GetMapping("/upload")
	public String upload()
	{
		return "upload";
	}
	
	
	@PostMapping("/index2")
	public String index2(Users user,BindingResult result)
	{    
		 user.getUsername();
		 user.getPassword();
	    // user.setLastLogin("Oggi");
	     ur_.save(user);
	     System.out.println("ADSF");
		return "index";
	}
	
	@GetMapping("/test")
	@ResponseBody
	public String test()
	{
		File homeBase = new File( System.getProperty( "user.home" ) ).getAbsoluteFile();
		File propertyFile = new File( homeBase, "files" );

		 String location = propertyFile.toString();
		 return location;
	}
	
	
	@GetMapping("/test2")
	@ResponseBody
	public String test2()
	{
		return storageProperties.getLocation();
	} 
	
	
	@GetMapping("/index")
	public String index(Users user)
	{
		return "index";
	}
	@GetMapping("/notLogin")
	public String notLog()
	{
		return "index";
	}
	
	@PostMapping("/upload-file")
	public String upl()
	{
		return "index";
	}
	
	
	
	@GetMapping ("/accesso")
	public String accesso (Accesso accesso)
	{
		return "accesso";
	}
	
	@PostMapping("/accesso")
	public String accessoPost (Accesso accesso, Model model)
	{
		r.save(accesso);
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		Sessione user = new Sessione(currentUserName);
		System.out.println(user.getUser());
		model.addAttribute("users", accesso);
		model.addAttribute("sessione", user);
		return "welcome-user";
		
	}
	
	
	@GetMapping ("/adduser")
	public String adduser(Users user,Model model)
	{
		//return "add-user";
		//model.addAttribute("errore", "A");
		return "registrati";
	}
	
	
	@PostMapping("/adduser")
	//@ResponseBody
	public String adduser_(Users user, Model model)
	//public String adduser(Users user,BindingResult result, Model model)
	{
		if (ur_.findByUsername(user.getUsername())==null)
		{
		if (   ( user.getUsername().isEmpty()) || (user.getPassword().isEmpty())  )  
			{
				user.setUsername(null);
	            user.setPassword(null);
				return "registrati";
			}
		else {
		
		
		user.setEnabled(true);
		ur_.save(user);
		autRep.save(new Authorities(user.getUsername(),"ROLE_USER"));
		
		model.addAttribute("users", user);
		
		//return "welcome-user";
		return "redirect:/lista";
		}
		}
		else {
			user.setUsername(null);
            user.setPassword(null);
			model.addAttribute("errore","Username già esistente");
			return "registrati";
		}
		//return "Ciao " + user.getUsername() + "con pw: "+ user.getPw();
	}
	
	
	@GetMapping("/account")
	public String account()
	{
		return "account";
	}
	
	@PostMapping("/upload")
	public String handleFileUpload(Authentication auth,@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws IOException {
		try
		{
		//aggiunta inizio
	/*	File homeBase = new File( System.getProperty( "user.home" ) ).getAbsoluteFile();
		File propertyFile = new File( homeBase, "files" );
*/
		//per creare la directory se non esistente
		if (System.getProperty("os.name").equals("Windows 10"))
		{
		//Files.createDirectories(Paths.get(propertyFile.toString()));
			Files.createDirectories(Paths.get(storageProperties.getLocation()+"/"+auth.getName()));
			
		
		}
		//aggiunta finita
		
		storageService.store(auth,file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		
		//salva nel db nomi files
		/*FilesUser nomeFile = new FilesUser();
		
		nomeFile.setNomefile(file.getOriginalFilename());
		nomeFile.setUser(auth.getName());
		
		fr.save(nomeFile);*/
		
		return "redirect:/lista";
		}
		catch (Exception e)
		{
		return e.getMessage().toString();
	}

	}
	
	
	@GetMapping("/lista")
	public String lista(Authentication auth,Model model) {
		
		try {
			Files.createDirectories(Paths.get(storageProperties.getLocation()+"/"+auth.getName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		File homeBase = new File( System.getProperty( "user.home" ) ).getAbsoluteFile();
		File propertyFile = new File( homeBase, "files" );
		//String dir = propertyFile.toString();
	    String dir = storageProperties.getLocation()+"/"+auth.getName();
		
		Set<String> listaFile=Stream.of(new File(dir).listFiles())
			      .filter(file -> !file.isDirectory())
			      .map(File::getName)
			      .collect(Collectors.toSet());
		
		 model.addAttribute("files", listaFile);
		
		 return "lista";
	}
	
	
	@GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(Authentication auth,@PathVariable String filename) {
       
      try
      {
		Resource resource = storageService.loadAsResource(auth,filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
      }
      catch (Exception e)
      {
    	  
    	    return ResponseEntity.badRequest().body(null);
      }
    }
	
	
	//path per cancellare il file
	@GetMapping("/delete/{filename:.+}")
    public String deleteFile(Authentication auth,@PathVariable String filename) {
       
        try {
			storageService.delete(auth,filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "error";
		}
        
        return "redirect:/lista";
       
               
    }
	
	
	
	@GetMapping("/salva")
	@ResponseBody
	public String salva() throws IOException
	{
		 LocalDate myObj = LocalDate.now(); // Create a date object
		 
		 Registrazioni reg = new Registrazioni(1l,Uuid.getUuid(), "user", "ps", myObj,1);
		 try {
		 registrazioniService.save(reg);
		 return "Fatto";
			
		 }
		 catch (Exception e)
		 {
			 Registrazioni reg2 = registrazioniService.findByName(Uuid.getUuid());
			 String res ="Esiste già una registrazione effettuata da questo dispositivo in data "+reg2.getDate() ; 
			 String res2 ="Il periodo di prova è scaduto";

			 if (myObj.isAfter(reg2.getDate().plusDays(1)) ) {
				 return res2;
			 }
			 else
			 {return res;}
			 }
			 
			 
}
		 
		 
		
	
	
	/*@GetMapping("/delete")
	public String delete()
	{
		return "error";
	}
	
	*/
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
	
	
	
	@GetMapping("/windows")
	@ResponseBody
	public boolean isWindows()
	{
	   return (System.getProperty("os.name").equals("Windows 10"));
	}
	
	
	@GetMapping("/os")
	@ResponseBody
	public String os()
	{
	   return (System.getProperty("os.name").toString());
	}
	
	@GetMapping("/registrati")
	public String registrati()
	{
		return "endpoint";
	}
	
	
	@GetMapping("/registra")
	public String reg ()
	{
		Utente u = new Utente(1l, "mattia", "mattia");
		try {
		ur2.save(u);
		}
		catch (Exception e) {}
		return "redirect:/";
	}
	
	
	

}
	
	
	

