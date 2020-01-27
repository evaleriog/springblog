package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StringTransformController {
    @GetMapping("/string/reverse/{string}")
    @ResponseBody
    public String reverse(@PathVariable String string){
        String reverse = "";

        for(int x = string.length() - 1; x >= 0; x--){
            reverse += string.charAt(x);
        }
        return reverse;
    }

    @GetMapping("/string/uppercase/{string}")
    @ResponseBody
    public String uppercase(@PathVariable String string){
        return string.toUpperCase();
    }

    @GetMapping("/string/both/{string}")
    @ResponseBody
    public String both(@PathVariable String string){
        string = reverse(string);
        string = uppercase(string);

        return string;
    }

    @GetMapping("/string/{string}")
    @ResponseBody
    public String handleInput(@PathVariable String string,
                              @RequestParam(value = "reverse", required = false) boolean reverse,
                              @RequestParam(value = "caps", required = false) boolean caps){

        if(reverse){
            string = reverse(string);
        }
        if(caps){
            string = uppercase(string);
        }

        return string;
    }
}
