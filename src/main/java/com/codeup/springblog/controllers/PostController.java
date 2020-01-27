package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        return "Index is 1";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String posts(@PathVariable int id){
        return "Viewing post # " + id;
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
