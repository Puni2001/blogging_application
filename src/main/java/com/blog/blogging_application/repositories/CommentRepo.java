package com.blog.blogging_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogging_application.entities.Comment;

public interface CommentRepo  extends JpaRepository<Comment	, Integer> {

}
