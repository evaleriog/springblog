package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

//    @OneToOne
//    private PostDetail postDetail;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
//    private List<PostImage> images;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post(){}

    public Post(long id, String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, User user){
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getBody(){
        return this.body;
    }

    public void setBody(String body){
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //    public PostDetail getPostDetail(){
//        return this.postDetail;
//    }

//    public List<PostImage> getImages(){
//        return this.images;
//    }
//
//    public void setImages(List<PostImage> images) {
//        this.images = images;
//    }
}
