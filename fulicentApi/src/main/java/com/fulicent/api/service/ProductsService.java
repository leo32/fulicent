package com.fulicent.api.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.annotations.Param;

import com.fulicent.api.dao.ProductsDao;
import com.fulicent.api.entity.Products;
import com.fulicent.common.entity.Pagination;

import java.util.Arrays;
import java.util.List;

@Named
public class ProductsService {
	private  ProductsDao productsDao;
	
	@Inject
	public ProductsService(ProductsDao productsDao){
		this.productsDao=productsDao;
	}
	
	 public List<Products> Products(int limit, int skip, String sort, String order, String categoryId,String type,String recommend, String brand,String ids,Pagination pagination) {
		String[] idList={};
		if(!ids.isEmpty()){
			idList=ids.split(",");
		}
		
		List<Products> products=null;
		switch(type.toLowerCase()){
		 	case "top": 
		 		products=productsDao.TopProducts(limit, skip,categoryId);
		 		pagination.setCount(productsDao.CountTopProducts(categoryId));
		 		break;
		 	case "recommend":
		 		products=productsDao.Products(limit, skip, sort, order, categoryId,recommend,brand);
		 		pagination.setCount(productsDao.Count(categoryId, recommend, brand, idList));
		 		break;
		 	case "my":
		 		products=productsDao.MyProducts( limit,  skip, idList);
		 		pagination.setCount(productsDao.Count("", "", "", idList));
		 		break;
		 	default: 
		 		products=productsDao.Products(limit, skip, sort, order, categoryId,recommend,brand);
		 		pagination.setCount(productsDao.Count(categoryId, recommend, brand, idList));
		 		break;
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
	 
	 public List<Products> MyProducts(int limit, int skip, String ids,Pagination pagination){
		String[] idList={};
		if(!ids.isEmpty()){
			idList=ids.split(",");
		}
		pagination.setCount(productsDao.Count("", "", "", idList));
		List<Products> products=productsDao.MyProducts( limit,  skip,   idList);
		return products;
	 }
}
