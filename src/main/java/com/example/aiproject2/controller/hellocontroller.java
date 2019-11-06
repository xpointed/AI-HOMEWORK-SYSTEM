package com.example.aiproject2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class hellocontroller {

    @RequestMapping("/index")
    public String sayHello(){
        return "MAIN";
    }
}