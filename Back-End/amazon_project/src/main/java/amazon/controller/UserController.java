package amazon.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import amazon.entities.User;
import amazon.service.UserService;

@RestController
@RequestMapping("/amazon/users")

@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PATCH})
public class UserController {

	
@Autowired
private UserService userService;	
	
	/*
	 * Register user
	 * 
	 * collects user data in body
	 * 
	 * create new user
	 * 
	 * returns status of registration
	 * 
	 */
	
	@PostMapping("/add")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		try 
		{
			User registeredUser=userService.registerUser(user);
			
			return new ResponseEntity<>("User Register Successfully", HttpStatus.OK);
		}
		
		catch (Exception e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//forgot password
	
	@PatchMapping("/forgot")
	public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> credentails)
	{
		String username=credentails.get("username");
		String email=credentails.get("email");
		
		try {
			
			userService.forgotPassword(username, email);
			return new ResponseEntity<>("Your Email is set as your password", HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}

	
	
	//Change PAssword
	
	@PatchMapping("/change/{userId}")
	
	public ResponseEntity<?> changePassword(@PathVariable int userId,@RequestBody Map<String, String> data)
	{
		String oldPassword=data.get("oldPassword");
		String newPassword=data.get("newPassword");
		
		try
		{
			User user=userService.changePassword(userId, oldPassword, newPassword);
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

	
	//Login form
	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String>credentails)
	{
		String username=credentails.get("username");
		String password=credentails.get("password");
		
		
		try {
			User user=userService.login(username, password);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	

	
	


