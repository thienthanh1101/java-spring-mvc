package vn.huynvit.sell.controller.Admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.huynvit.sell.domain.Product;
import vn.huynvit.sell.service.ProductService;
import vn.huynvit.sell.service.UploadService;

@Controller
public class ProductController {
    // DI dependency injection
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @RequestMapping("/admin/product")
    public String getProduct(Model model) {
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products1", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping(value = "/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product pr,
            BindingResult newUserbindingResult, @RequestParam("laptopFile") MultipartFile file) {
        // validate
        if (newUserbindingResult.hasErrors()) {
            return "admin/product/create";
        }
        // upload image
        String image = this.uploadService.handleSaveUploadFile(file, "product");
        pr.setImage(image);
        this.productService.createProduct(pr);
        return "redirect:/admin/product";
    }

}
