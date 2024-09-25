package vn.huynvit.sell.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huynvit.sell.domain.Product;

// Cru: Create, Update, Read, Delete
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
