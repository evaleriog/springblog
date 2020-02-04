package com.codeup.springblog.controllers;
import com.codeup.springblog.SpringblogApplication;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringblogApplication.class)
@AutoConfigureMockMvc
public class PostIntegrationTests {
    private HttpSession httpSession;
    private User testUser;

    @Autowired
    PostRepository postDao;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception{
        testUser = usersDao.findByUsername("testUser");

        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = usersDao.save(newUser);
        }

        httpSession = this.mvc.perform(post("/login").with(csrf())
                .param("username", "testUser")
                .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/posts"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void contextLoads(){
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        // It makes sure the returned session is not null
        assertNotNull(httpSession);
    }

    @Test
    public void testPostIndex() throws Exception {
        Post existingPost = postDao.findAll().get(0);
        this.mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("View of all Posts")))
                .andExpect(content().string(containsString(existingPost.getTitle())));
    }

    @Test
    public void testShowPost() throws Exception{
        Post existingPost = postDao.findAll().get(0);

        this.mvc.perform(get("/posts/" + existingPost.getId()).session((MockHttpSession) httpSession))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(existingPost.getBody())));
    }

    @Test
    public void testCreatePost() throws Exception{
        this.mvc.perform(
                post("/posts/create").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title", "test JUnit")
                .param("body", "junit sale"))
                .andExpect(status().is3xxRedirection()
        );
    }

    @Test
    public void testEditPost() throws Exception{
        Post existingPost = postDao.findAll().get(5);

        this.mvc.perform(
                post("/update/" + existingPost.getId()).with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title", "edited test JUNit")
                .param("body", "edited junit sale")
                        .param("user", Long.toString(existingPost.getUser().getId())))
                .andExpect(status().is3xxRedirection()
        );
    }

    @Test
    public void testDeletePost() throws Exception{
        this.mvc.perform(
                post("/posts/create").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title", "Eduardo")
                .param("body", "valerio"))
                .andExpect(status().is3xxRedirection()
        );

        Post existingPost = postDao.findAll().get(postDao.findAll().size() - 1);

        this.mvc.perform(get("/delete/" + existingPost.getId()).session((MockHttpSession) httpSession))
                .andExpect(status().isOk()
        );
    }


}

