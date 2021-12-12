package com.xiaxinyu.myblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @GetMapping("/")
    public String index(){
        int a = 9 / 0;
        return "index";
    }
}
