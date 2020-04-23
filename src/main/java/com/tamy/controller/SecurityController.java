package com.tamy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SecurityController {
//    要添加Thymeleaf依赖，不然就会报错，Path[/index]
    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/level1/{id}")
    public String level1(@PathVariable("id") int id){
        return "pages/level1/"+id;
    }
    @RequestMapping("/level2/{id}")
    public String level2(@PathVariable("id") int id){
        return "pages/level2/"+id;
    }
    @RequestMapping("/level3/{id}")
    public String level3(@PathVariable("id") int id){
        return "pages/level3/"+id;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("logout")
    public String logout(){
        return "logout";
    }
}
