package BookMyTurf.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BookMyTurf.entity.User;
import BookMyTurf.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {

        System.out.println("NAME = " + user.getName());
        System.out.println("EMAIL = " + user.getEmail());

        System.out.println("ROLE OBJECT = " + user.getRole());

        if (user.getRole() != null) {
            System.out.println("ROLE ID = " + user.getRole().getId());
        }

        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}