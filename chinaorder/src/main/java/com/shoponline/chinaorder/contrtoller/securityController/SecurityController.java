package com.shoponline.chinaorder.contrtoller.securityController;

import com.shoponline.chinaorder.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class SecurityController {

    String template = "admin/templateAdmin";
    public String Redirect(String url, Object success) {
        if (success instanceof Boolean) {
            if((boolean) success){
                if(url.contains("?")){
                    String successParam = "&success=" + success;
                    return "redirect:/" + url + successParam;
                } else {
                    String successParam = "?success=" + success;
                    return "redirect:/" + url + successParam;
                }
            } else{
                if(url.contains("?")){
                    String successParam = "&unsuccess=" + true;
                    return "redirect:/" + url + successParam;
                } else {
                    String successParam = "?unsuccess=" + true;
                    return "redirect:/" + url + successParam;
                }
            }
        } else {
            return "redirect:/" + url;
        }
    }
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public SecurityController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String Login(Model model) {
        List<String> userDB = new ArrayList<>();
        userService.getAllUsers().forEach(user -> {
            userDB.add(user.getUsername());
        });
        model.addAttribute("userDB", userDB);
        return "security/login";
    }
    @GetMapping("/access-denied")
    public String Accessdenied() {
        return "security/access-denied";
    }
    @GetMapping("/Lengkeng/register")
    public String register() {
        return "security/register";
    }
    @GetMapping("/Lengkeng/forgotten_password")
    public String forgotten_password() {
        return "security/forgotten-password";
    }
//    @PostMapping("/register")
//    public String Register(@RequestParam("name") String name,
//                           @RequestParam("rank") String rank,
//                           @RequestParam("unit") String unit,
//                           @RequestParam("militaryNumber") long militaryNumber,
//                           @RequestParam("contact") String contact,
//                           @RequestParam("usernameReg") String usernameReg,
//                           @RequestParam("password") String password,
//                           @RequestParam("confirmPassword") String confirmPassword){
//        People people = new People(name, rank, unit, militaryNumber, contact, 1);
//        peopleService.createPeople(people);
//        Authority authority = new Authority(usernameReg, "ROLE_TEACHERWAIT");
//        authorityService.createAuthority(authority);
//        Users user = new Users(usernameReg, passwordEncoder.encode(password), people.getId(), 0);
//        userService.createUser(user);
//        return Redirect("login",true);
//    }

}

