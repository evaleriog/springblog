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
        all.add(new Post(1,"Best Vacation Ever", "So much fun I had in the best place"));
        all.add(new Post(2,"Computer", "I just bought a Mac"));
        all.add(new Post(3,"Food", "Best tacos ever"));
        all.add(new Post(4,"Java", "I just learner java"));
        all.add(new Post(5,"SHoes", "Bought new shoes"));
        all.add(new Post(6,"Best Parks", "Here it is a list of best parks"));
        all.add(new Post(7,"Codeup", "Coding Bootcamp"));

        model.addAttribute("posts", all);

        return "/posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    //@ResponseBody
    public String viewPost(@PathVariable int id, Model model){
        String title = "I need help with my resume";
        String body = "I am new to this element of creating a resume. Please" +
                " someone help me";
        Post post = new Post(id,title, body);

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
