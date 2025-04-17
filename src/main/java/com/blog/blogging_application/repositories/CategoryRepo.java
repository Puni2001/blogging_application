package com.blog.blogging_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogging_application.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
