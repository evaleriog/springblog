package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
//    @Modifying
//    @Query("update post p set p.title = ?1, p.description = ?2 where p.id = ?3")
//    int updatePostById(Post post);


}
