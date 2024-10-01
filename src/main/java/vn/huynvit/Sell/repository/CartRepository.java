package vn.huynvit.sell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.huynvit.sell.domain.User;
import vn.huynvit.sell.domain.dto.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
