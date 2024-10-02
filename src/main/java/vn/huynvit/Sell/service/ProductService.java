package vn.huynvit.sell.service;

import java.util.List;

import java.util.Optional;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.huynvit.sell.domain.Cart;
import vn.huynvit.sell.domain.CartDetail;
import vn.huynvit.sell.domain.Product;
import vn.huynvit.sell.domain.User;
import vn.huynvit.sell.repository.CartDetailRepository;
import vn.huynvit.sell.repository.CartRepository;
import vn.huynvit.sell.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
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

    public void handleAddProductToCart(String email, long productId, HttpSession session) {
        // check user đã có cart chưa? nếu chưa -->tạo mới
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            // check user đã có cart chưa? nếu chưa -->tạo mới
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                // Tạo mới cart
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);
                cart = this.cartRepository.save(otherCart);
            }
            // Save cart detail
            // tìm product by id
            Optional<Product> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product realProduct = productOptional.get();
                // check sản phẩm đã từng được thêm vào giỏ hàng trước đây chưa ?
                // boolean isExistProductInCart =
                // this.cartDetailRepository.existsByCartAndProduct(cart, realProduct);
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                //
                if (oldDetail == null) {
                    CartDetail cd = new CartDetail();
                    cd.setCart(cart);
                    cd.setProduct(realProduct);
                    cd.setPrice(realProduct.getPrice());
                    cd.setQuantity(1);
                    this.cartDetailRepository.save(cd);

                    // update cart (sum);
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);

                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldDetail);
                }

            }

        }

    }

    // public void deleteProduct(long id) {
    // this.productRepository.deleteById(id);
    // }

    public Cart fectchByUser(User user) {
        return this.cartRepository.findByUser(user);
    }
}
