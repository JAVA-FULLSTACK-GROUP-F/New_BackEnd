package unimap.groupf.zerohunger;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User register(User user) {
        // You can perform validation and encryption logic here
        return userRepository.save(user);
    }
    
    public boolean login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // You can perform decryption and comparison logic here
            return user.getPassword().equals(password);
        }
        return false;
    }
}