package com.demo.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class demoController {
    @GetMapping
    public String demoController(){
        return "demo";
    }
}
