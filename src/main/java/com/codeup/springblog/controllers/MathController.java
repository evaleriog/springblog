package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/divide/{num1}/by/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable int num1, @PathVariable int num2){
        try{
            if(num1 == 0 || num2 == 0){
                throw new ArithmeticException();
            }

            int response = num1 / num2;

            return "The division is " + response;

        }catch (Exception e){
            e.printStackTrace();

            return "Error";
        }

    }
}
