package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    //@ResponseBody
    public String posts(Model model){
        model.addAttribute("posts", postDao.findAll());

        return "/posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    //@ResponseBody
    public String viewPost(@PathVariable int id, Model model){
        long find = id;
        model.addAttribute("post", postDao.getOne(find));
        return "/posts/show";
    }

    @GetMapping(path = "/delete/{id}")
    public String deletePost(@PathVariable long id){
        //postDao.deleteById(id);
        postDao.delete(postDao.getOne(id));
        return "redirect:/posts";
    }

    @GetMapping("/update/{id}")
    public String updatePostGet(@PathVariable long id, Model model){
        Post post = postDao.getOne(id);
        model.addAttribute("post", post);
        return "posts/update";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable long id,
                             @RequestParam(name = "title")String title,
                             @RequestParam(name = "body") String body){
        Post post = postDao.getOne(id);
        post.setTitle(title);
        post.setBody(body);

        postDao.save(post);

        String path = "redirect:/posts/" + post.getId().toString();
        return path;
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
