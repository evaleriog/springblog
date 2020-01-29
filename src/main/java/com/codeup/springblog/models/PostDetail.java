package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "post_details")
public class PostDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private long id;

    private boolean isAwesome;

    @Column(columnDefinition = "TEXT")
    private String historyOfPost;

    private String topicDescription;

    @OneToOne(mappedBy = "postDetail")
    private Post post;

    public PostDetail(){}

    public PostDetail(long id, boolean isAwesome, String historyOfPost, String topicDescription){
        this.id = id;
        this.isAwesome = isAwesome;
        this.historyOfPost = historyOfPost;
        this.topicDescription = topicDescription;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public boolean getAwesome(){
        return this.isAwesome;
    }

    public void setAwesome(boolean isAwesome){
        this.isAwesome = isAwesome;
    }

    public String getHistoryOfPost(){
        return this.historyOfPost;
    }

    public void setHistoryOfPost(String historyOfPost){
        this.historyOfPost = historyOfPost;
    }

    public String getTopicDescription(){
        return this.topicDescription;
    }

    public void setTopicDescription(String topicDescription){
        this.topicDescription = topicDescription;
    }
}
