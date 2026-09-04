package BookMyTurf.service;

import org.springframework.stereotype.Service;
import java.util.List;

import BookMyTurf.entity.User;
import BookMyTurf.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import BookMyTurf.exception.EmailAlreadyExistsException;
@Service
public class UserService 
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) 
    {
    	
    	if (userRepository.existsByEmail(user.getEmail())) 
    	{
    		throw new EmailAlreadyExistsException("Email already exists");
           
     }
    	
    	user.setStatus("ACTIVE");
    	
    	String encodedPassword = passwordEncoder.encode(user.getPassword());
    	
    	    user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() 
    {
        return userRepository.findAll();
    }
}