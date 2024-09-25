package vn.huynvit.sell.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.huynvit.sell.domain.Product;
import vn.huynvit.sell.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public Product createProduct(Product product) {
        Product huy = this.productRepository.save(product);
        System.out.println(huy);
        return huy;
    }

}
