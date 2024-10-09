package com.shoponline.chinaorder.contrtoller.mainController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Lengkeng")
public class mainController {
    @GetMapping({"/", ""})
    public String Lengkeng(){
        return "visitors/index";
    }
    @GetMapping("/accouunt")
    public String accouunt(){
        return "visitors/cart";
    }
    @GetMapping("/cart")
    public String cart(){
        return "visitors/cart";
    }
    @GetMapping("/category")
    public String category(){
        return "visitors/category";
    }
    @GetMapping("/product")
    public String product(){
        return "visitors/product";
    }
    @GetMapping("/checkout")
    public String checkout(){
        return "visitors/checkout";
    }
    @GetMapping("/checkout_payment")
    public String checkoutPayment(){
        return "visitors/checkout-payment";
    }
    @GetMapping("/checkout_shipping")
    public String checkoutShipping(){
        return "visitors/checkout-shipping";
    }
}
