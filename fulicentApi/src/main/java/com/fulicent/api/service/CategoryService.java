package com.fulicent.api.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.fulicent.api.dao.CategoryDao;
import com.fulicent.api.entity.Category;

@Named
public class CategoryService {
	private  CategoryDao categoryDao;
	
	@Inject
	public CategoryService(CategoryDao categoryDao){
		this.categoryDao=categoryDao;
	}
	
	 public List<Category> Category(int limit, int skip, String sort, String order) {
		 List<Category> category=categoryDao.Category(limit, skip, sort, order);
		 return category;
	 }
	 
	 public int SaveCategory(Category category){
		 return categoryDao.SaveCategory(category);
	 }
}
