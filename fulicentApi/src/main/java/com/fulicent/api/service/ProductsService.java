package com.fulicent.api.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.fulicent.api.dao.ProductsDao;
import com.fulicent.api.entity.Products;

import java.util.Arrays;
import java.util.List;

@Named
public class ProductsService {
	private  ProductsDao productsDao;
	
	@Inject
	public ProductsService(ProductsDao productsDao){
		this.productsDao=productsDao;
	}
	
	 public List<Products> Products(int limit, int skip, String sort, String order, String type) {
		 List<Products> Products=productsDao.Products(limit, skip, sort, order, type);
		 return Products;
	 }
}
