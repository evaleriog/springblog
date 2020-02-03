package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.TagRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final TagRepository tagDao;
    private final EmailService emailServiceDao;

    public PostController(PostRepository postDao, UserRepository userDao, TagRepository tagDao, EmailService emailServiceDao){
        this.postDao = postDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
        this.emailServiceDao = emailServiceDao;
    }

    @GetMapping("/posts")
    //@ResponseBody
    public String posts(Model model){
        model.addAttribute("posts", postDao.findAll());

        return "/posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    //@ResponseBody
    public String viewPost(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "/posts/show";
    }

    @GetMapping(path = "/delete/{id}")
    public String deletePost(@PathVariable long id){
        //postDao.deleteById(id);
        postDao.delete(postDao.getOne(id));
        return "redirect:/posts";
    }

    @GetMapping("/details/{id}")
    public String detailsByPost(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/details";
    }

    @GetMapping("images/{id}")
    public String images(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/images";
    }

    @GetMapping("/update/{id}")
    public String updatePostGet(@PathVariable long id, Model model){
        Post post = postDao.getOne(id);
        model.addAttribute("post", post);
        return "posts/update";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@ModelAttribute Post post){

        postDao.save(post);

        return "redirect:/posts/" + post.getId().toString();
    }

    @GetMapping("/posts/create")
    public String postsCreate(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreatePost(@ModelAttribute Post post){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postDao.save(post);
        String body ="You just created a post with the title: " + post.getTitle() + ", and content: " + post.getBody();
        emailServiceDao.preparedAndSend(post,
                "You created a new post",
                body);
        return "redirect:/posts";
    }

    @GetMapping("tags/{id}")
    public String tagById(@PathVariable long id, Model model){
        model.addAttribute("tag", tagDao.getOne(id));
        return "/posts/tag";
    }
}
