package com.fulicent.api.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.annotations.Param;

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
	
	 public List<Products> Products(int limit, int skip, String sort, String order, String categoryId,String type,String recommend, String brand) {

		 List<Products> products=null;
		 switch(type.toLowerCase()){
		 	case "top": products=productsDao.TopProducts(limit, skip);break;
		 	case "my":break;
		 	default: products=productsDao.Products(limit, skip, sort, order, categoryId,recommend,brand);break;
		 }
		 return products;
	 }
	 
	 public Products Product(int id){
		 Products product=productsDao.Product(id);
		 return product;
	 }
}
