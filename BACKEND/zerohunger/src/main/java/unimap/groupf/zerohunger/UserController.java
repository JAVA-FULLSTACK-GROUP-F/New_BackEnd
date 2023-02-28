package unimap.groupf.zerohunger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAllByOrderByUsername();
    }
    
    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
    
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
    
    @GetMapping("/users/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
    
    @PutMapping("/users/{id}/username")
    public void updateUsername(@PathVariable Long id, @RequestBody String newUsername) {
        userRepository.updateUsername(id, newUsername);
    }
    
    @PutMapping("/users/{id}/phonenumber")
    public void updatePhoneNumber(@PathVariable Long id, @RequestBody String newPhoneNumber) {
        userRepository.updatePhoneNumber(id, newPhoneNumber);
    }
    
    @PutMapping("/users/{id}/password")
    public void updatePassword(@PathVariable Long id, @RequestBody String newPassword) {
        userRepository.updatePassword(id, newPassword);
    }
    
    @GetMapping("/users/all")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }
    
    @GetMapping("/users/find")
    public List<User> findUsers(@RequestParam String query) {
        return userRepository.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }
    
    @GetMapping("/users/count")
    public long countUsers(@RequestParam String query) {
        return userRepository.countByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }
}
