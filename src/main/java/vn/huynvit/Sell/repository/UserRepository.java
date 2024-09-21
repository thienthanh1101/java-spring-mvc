package vn.huynvit.sell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huynvit.sell.domain.User;

import java.util.List;

//Cru: Create, Update, Read, Delete
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User huynv75);

    void deleteById(long id);

    List<User> findByEmail(String email);

    List<User> findAll();

    User findById(long id);// not null if not use this function, we use default of spring
}
