package com;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.User;
import com.UserRepository;

@Controller
public class controllerPages {
	private UserRepository userRepository;	
	public controllerPages (UserRepository userRepository)
	{
		this.userRepository=userRepository;
	}
	@GetMapping("/")
	public String main()
	{
		return "main";
	}
	
	@GetMapping("/user")
	public String add(User user)
	{
		return "add-user";
	}
	
	
	
	@PostMapping("/adduser")
    public String addUser(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        userRepository.save(user);
        return "redirect:/index";
    }

	@GetMapping("/list")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "list-user";
    }
	
	
	@PostMapping("/index")
	public String index2()
	{
		return "index";
	}
	
	
	@GetMapping("/index")
	public String index()
	{
		return "index";
	}
	@GetMapping("/notLogin")
	public String notLog()
	{
		return "index";
	}
}
