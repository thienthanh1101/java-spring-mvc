package vn.huynvit.sell.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.huynvit.sell.domain.Role;
import vn.huynvit.sell.domain.User;
import vn.huynvit.sell.domain.dto.RegisterDTO;
import vn.huynvit.sell.repository.RoleRepository;
import vn.huynvit.sell.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void DeleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public User handleSaveUser(User user) {
        User huy = this.userRepository.save(user);
        System.out.println(huy);
        return huy;
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);

    }

    // mapping DTO
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }
}
