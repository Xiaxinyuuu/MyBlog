package com.xiaxinyu.myblog.web;

import com.xiaxinyu.myblog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class indexController {
    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id,@PathVariable String name){
        System.out.println("-----index-----");
        return "index";
    }
}
