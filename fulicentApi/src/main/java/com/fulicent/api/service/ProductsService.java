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
	
	 public List<Products> Products(int limit, int skip, String sort, String order, String categoryId,String type,String recommend, String brand,String ids) {
		String[] idList={};
		if(!ids.isEmpty()){
			idList=ids.split(",");
		}
		List<Products> products=null;
		switch(type.toLowerCase()){
		 	case "top": products=productsDao.TopProducts(limit, skip,categoryId);break;
		 	case "recommend":products=productsDao.Products(limit, skip, sort, order, categoryId,recommend,brand);break;
		 	case "my":products=productsDao.MyProducts( limit,  skip, idList);break;
		 	default: products=productsDao.Products(limit, skip, sort, order, categoryId,recommend,brand);break;
		}
		return products;
	 }
	 
	 public Products Product(int id){
		 Products product=productsDao.Product(id);
		 return product;
	 }
	 
	 public List<Products> AdProducts(int limit, int skip){
		 List<Products> products=productsDao.AdProducts(limit, skip);
		 return products;
	 }
	 
	 public List<Products> MyProducts(int limit, int skip, String[] ids){
		 List<Products> products=productsDao.MyProducts( limit,  skip,   ids);
		 return products;
	 }
}
