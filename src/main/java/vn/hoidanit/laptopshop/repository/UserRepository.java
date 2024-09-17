package vn.hoidanit.laptopshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;
import java.util.List;

//Cru: Create, Update, Read, Delete
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User save(User huynv75);

    List<User> findByEmail(String email);

    List<User> findAll();
}
