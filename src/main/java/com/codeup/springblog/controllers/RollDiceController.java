package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice(){
        return "rolldice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDiceN(@PathVariable int n, Model model){
        Random random = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        Random random4 = new Random();

        int guess = random.nextInt(6) + 1;
        int guess2 = random.nextInt(6) + 1;
        int guess3 = random.nextInt(6) + 1;
        int guess4 = random.nextInt(6) + 1;

        int counter = 0;

        model.addAttribute("userNumber", "The number you selected was " + n);

        model.addAttribute("guess", "Your number to guess for Dice 1 was " + guess);
        model.addAttribute("guess2", "Your number to guess for Dice 2 was " + guess2);
        model.addAttribute("guess3", "Your number to guess for Dice 3 was " + guess3);
        model.addAttribute("guess4", "Your number to guess for Dice 4 was " + guess4);


        if(n == guess){
            counter++;
            model.addAttribute("response", "You guessed the number Correctly");
        }else{
            model.addAttribute("response", "Your guess was incorrect");
        }

        if(n == guess2){
            counter++;
            model.addAttribute("response2", "You guessed the number Correctly");
        }else{
            model.addAttribute("response2", "Your guess was incorrect");
        }

        if(n == guess3){
            counter++;
            model.addAttribute("response3", "You guessed the number Correctly");
        }else{
            model.addAttribute("response3", "Your guess was incorrect");
        }

        if(n == guess4){
            counter++;
            model.addAttribute("response4", "You guessed the number Correctly");
        }else{
            model.addAttribute("response4", "Your guess was incorrect");
        }

        model.addAttribute("total", "You guessed a total of " + counter + " dice.");

        return "rolldice";
    }
}
