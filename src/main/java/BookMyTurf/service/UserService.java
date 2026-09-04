package BookMyTurf.service;

import org.springframework.stereotype.Service;
import java.util.List;
import BookMyTurf.entity.User;
import BookMyTurf.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}