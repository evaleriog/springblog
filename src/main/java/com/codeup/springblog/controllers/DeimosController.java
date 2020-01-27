package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeimosController {
    @GetMapping("/deimos/{days}")

    @ResponseBody
    public String deimos(@PathVariable String days){
        return "We have " + days +" days left for developer day ";
    }
}
