package vn.huynvit.sell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huynvit.sell.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
