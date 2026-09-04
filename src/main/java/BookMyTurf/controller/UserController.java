package BookMyTurf.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BookMyTurf.dto.UserRequestDTO;
import BookMyTurf.dto.UserResponseDTO;
import BookMyTurf.entity.Role;
import BookMyTurf.entity.User;
import BookMyTurf.repository.RoleRepository;
import BookMyTurf.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping
    public UserResponseDTO createUser(
            @Valid @RequestBody UserRequestDTO userRequestDTO) {

        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setPhone(userRequestDTO.getPhone());

        Role role = roleRepository.findById(userRequestDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);

        User savedUser = userService.saveUser(user);

        UserResponseDTO response = new UserResponseDTO();

        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setPhone(savedUser.getPhone());
        response.setStatus(savedUser.getStatus());
        response.setRoleId(savedUser.getRole().getId());

        return response;
    }
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {

        List<User> users = userService.getAllUsers();

        return users.stream()
                .map(user -> {
                    UserResponseDTO response = new UserResponseDTO();

                    response.setId(user.getId());
                    response.setName(user.getName());
                    response.setEmail(user.getEmail());
                    response.setPhone(user.getPhone());
                    response.setStatus(user.getStatus());
                    response.setRoleId(user.getRole().getId());

                    return response;
                })
                .toList();
    
    }
    }