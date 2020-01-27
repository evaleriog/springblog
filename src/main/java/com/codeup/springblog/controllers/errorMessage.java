package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class errorMessage {
    @RequestMapping("/**")
    @ResponseBody
    public String mistake(){
        return "Page not found";
    }

    @RequestMapping("/wildcards" + "**")
    @ResponseBody
    public String mistake2(){
        return "Page not found";
    }

}
