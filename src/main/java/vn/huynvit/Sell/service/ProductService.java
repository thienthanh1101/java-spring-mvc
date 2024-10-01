package vn.huynvit.sell.service;

import java.util.List;

import java.util.Optional;
import org.springframework.stereotype.Service;
import vn.huynvit.sell.domain.Product;
import vn.huynvit.sell.repository.CartDetailRepository;
import vn.huynvit.sell.repository.CartRepository;
import vn.huynvit.sell.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
    }

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public Optional<Product> fetchProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public Product createProduct(Product product) {
        Product huy = this.productRepository.save(product);
        System.out.println(huy);
        return huy;
    }

    public void handleAddProductToCart() {

    }
}
