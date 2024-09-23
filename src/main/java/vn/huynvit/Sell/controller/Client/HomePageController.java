package vn.huynvit.sell.controller.Client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String getMethodName() {
        return new String("client/homepage/show");
    }

}
