package com.blog.blogging_application.services;

import java.util.List;

import com.blog.blogging_application.entities.Post;
import com.blog.blogging_application.payloads.PostDto;
import com.blog.blogging_application.payloads.PostResponse;

public interface PostService {

	//create 

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

	//update 

	PostDto updatePost(PostDto postDto, Integer postId);

	// delete

	void deletePost(Integer postId);
	
	//get all posts
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get single post
	
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);

}
