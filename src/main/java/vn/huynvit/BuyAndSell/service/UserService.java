package vn.huynvit.BuyAndSell.service;

import java.util.List;

import org.springframework.stereotype.Service;
import vn.huynvit.BuyAndSell.domain.User;
import vn.huynvit.BuyAndSell.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
