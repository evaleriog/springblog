package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "post_images")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostImage(){}

    public PostImage(long id, String title, String url){
        this.id = id;
        this.title = title;
        this.url = url;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Post getPost(){
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
