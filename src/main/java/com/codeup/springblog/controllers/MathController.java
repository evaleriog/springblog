package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String add(@PathVariable String num1, @PathVariable String num2){
        int response = Integer.parseInt(num1) + Integer.parseInt(num2);

        return "The addition is " + response;
    }

    @GetMapping("/substract/{num1}/from/{num2}")
    @ResponseBody
    public String substract(@PathVariable String num1, @PathVariable String num2){
        int response = Integer.parseInt(num2) - Integer.parseInt(num1);

        return "The substraction is " + response;
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiply(@PathVariable String num1, @PathVariable String num2){
        int response = Integer.parseInt(num1) * Integer.parseInt(num2);

        return "The multiplication is " + response;
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divide(@PathVariable String num1, @PathVariable String num2){
        int response = Integer.parseInt(num1) / Integer.parseInt(num2);

        return "The division is " + response;
    }
}
