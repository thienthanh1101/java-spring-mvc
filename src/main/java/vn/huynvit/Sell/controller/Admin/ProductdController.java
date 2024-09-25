package vn.huynvit.sell.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductdController {

    @GetMapping("/admin/product")
    public String getProduct(Model model) {
        return "admin/product/show";
    }

    // @GetMapping("/admin/product/create")
    // public String getCreateProductPage(Model model) {
    // model.addAttribute("newProduct", new Product());
    // return "admin/product/create";
    // }
}
