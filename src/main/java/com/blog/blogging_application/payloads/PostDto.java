package com.blog.blogging_application.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.blog.blogging_application.entities.Category;
import com.blog.blogging_application.entities.Comment;
import com.blog.blogging_application.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;	
	
	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();

	
	
	
	
	
	
}
