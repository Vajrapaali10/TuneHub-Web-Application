package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.UserService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import com.kodnest.tunehub.service.SongService;


@Controller
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	SongService songService;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) 
	{
		//to check a user with a mail is present or not
		boolean userExists= userService.emailExsists(user);
		
		
		if(userExists==false) {
	
		userService.saveUser(user);
		System.out.println("User added Successfully!");		
		}
		else
		{
			System.out.println("Duplicate user");
		}	
		return "login";
	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, 
	                       @RequestParam("password") String password, 
	                       HttpSession session, 
	                       Model model) {
	    
	    // Check if email or password is empty
	    if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
	        model.addAttribute("errorMessage", "Please enter both email and password.");
	        return "login";  // Redirect back to the login page
	    }
	    
	    // Validate user credentials
	    if (userService.validUser(email, password)) {
	        session.setAttribute("email", email);
	        
	        String role = userService.getRole(email);
	        
	        if (role.equals("admin")) {
	            return "adminhome";
	        } else {
	            User user = userService.getUser(email);
	            boolean userStatus = user.isPremium();
	            
	            List<Song> fetchAllSongs = songService.fetchAllSongs();
	            model.addAttribute("songs", fetchAllSongs);
	            model.addAttribute("ispremium", userStatus);
	            
	            // Add success message (if needed)
	            return "customerhome";
	        }
	    } else {
	        model.addAttribute("errorMessage", "Invalid email or password.");
	        return "login";  // Redirect back to the login page
	    }
	}



@GetMapping("/logout")
//httpsession used to store the  information of the user
	public String logout(HttpSession session) {
	session.invalidate();
		return "login";
	
	}

@GetMapping("/exploresongs")
public String exploresongs(String email) {
	return email;
	
	
}
}

