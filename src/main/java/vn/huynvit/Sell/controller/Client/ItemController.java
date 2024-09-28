package vn.huynvit.sell.controller.Client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.huynvit.sell.domain.Product;
import vn.huynvit.sell.service.ProductService;

@Controller
public class ItemController {
    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable long id) {
        Product pr = this.productService.fetchProductById(id).get();
        // System.out.println("Check user detail:" + pr);
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "client/product/detail";
    }
}
