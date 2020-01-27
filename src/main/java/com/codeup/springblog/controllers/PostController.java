package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    //@ResponseBody
    public String posts(Model model){
        List<Post> all = new ArrayList<>();
        all.add(new Post("Best Vacation Ever", "So much fun I had in the best place"));
        all.add(new Post("Computer", "I just bought a Mac"));
        all.add(new Post("Food", "Best tacos ever"));
        all.add(new Post("Java", "I just learner java"));
        all.add(new Post("SHoes", "Bought new shoes"));
        all.add(new Post("Best Parks", "Here it is a list of best parks"));
        all.add(new Post("Codeup", "Coding Bootcamp"));

        model.addAttribute("posts", all);

        return "/posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    //@ResponseBody
    public String posts(@PathVariable int id, Model model){
        String title = "I need help with my resume";
        String body = "I am new to this element of creating a resume. Please" +
                " someone help me";
        Post post = new Post(title, body);

        model.addAttribute("id", id);
        model.addAttribute("post", post);

        return "/posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postsCreate(){
        return "Creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String postCreatePost(){
        return "creating a new post";
    }
}
