package com.blog.blogging_application.services;

import java.util.List;

import com.blog.blogging_application.payloads.CategoryDto;

public interface CategoryService {

	// create
	CategoryDto createCategory(CategoryDto categoryDto);

	// update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete
	void deleteCategory(Integer categoryId);

	// get
	CategoryDto getCategory(Integer categoryId);

	// get All

	List<CategoryDto> getCategories();

}
