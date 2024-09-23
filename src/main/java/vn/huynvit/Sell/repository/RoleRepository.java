package vn.huynvit.sell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huynvit.sell.domain.Role;

//Cru: Create, Update, Read, Delete
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
