package amazon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazon.entities.User;
import amazon.repositories.UserRepositories;

@Service
public class UserService {

    @Autowired
    private UserRepositories userRepositories;

    public User registerUser(User user) {

        if (userRepositories.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists, try another");
        }

       else if (userRepositories.existsByEmail(user.getEmail())) 
        {
            throw new RuntimeException("Email already registered");
        }

        if (user.getRole()==null)
        {
			user.setRole("user");
		}
        
      return  userRepositories.save(user);
    }
    
    
    
    public void forgotPassword(String username,String email)
    {
    	if(!userRepositories.existsByUsername(username))
    	{
    		throw new RuntimeException("Invalid user name");
    	}
    	Optional<User> optionalUser=userRepositories.findByUsername(username);
    	
    	if (optionalUser.isPresent())
    	{
			User user=optionalUser.get();
			
			if (!user.getEmail().equals(email))
			{
				throw new RuntimeException("Invalid Email");
			}
			else {
				user.setPassword(email);
				
				userRepositories.save(user);
			}
		}
    }
    
    
    public User changePassword(int userId,String oldPassword,String newPassword)
    {
    	User user=null;
    	
    	if( !userRepositories.existsById(userId))
    	{
    		throw new RuntimeException("User does not exists");
    		
    	}
    	Optional<User> optionalUser=userRepositories.findById(userId);
    	if(optionalUser.isPresent())
    	{
    		user=optionalUser.get();
    		if(!user.getPassword().equals(oldPassword))
    		{
    			throw new RuntimeException("Invalid old password");
    			
    		}
    		else {
				user.setPassword(newPassword);
				userRepositories.save(user);
			}
    	}
    	
    	return user;
    }
    
    
    public User login(String username,String password)
    {
    	User user=null;
    	
    	Optional<User> optionalUser=userRepositories.findByUsername(username);
    	
    	if(!optionalUser.isPresent())
    	{
    		throw new RuntimeException("Invalid user name");
    	}
    	
    	user=optionalUser.get();
    	
    	if (!user.getPassword().equals(password)) {
    		throw new RuntimeException("invalid Password");
			
		}
    	
    	
    	return user;
    }
}




