package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello, Welcome to Spring!";
    }

    @GetMapping("/hello/{cohort}")
    @ResponseBody
    public String hello(@PathVariable String cohort){
        return "Hello " + cohort + ", Welcome to Spring!";
    }
}
