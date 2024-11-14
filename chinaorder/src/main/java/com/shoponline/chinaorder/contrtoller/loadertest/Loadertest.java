package com.shoponline.chinaorder.contrtoller.loadertest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Loadertest {
    @GetMapping("/loaderio-7d625a3b52df283e41a39dda31004aa4.txt")
    @ResponseBody
    public String loaderio() {
        return "loaderio-7d625a3b52df283e41a39dda31004aa4";
    }
}
