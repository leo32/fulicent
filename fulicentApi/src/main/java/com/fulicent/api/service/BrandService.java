package com.fulicent.api.service;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.fulicent.api.dao.BrandDao;
import com.fulicent.api.entity.Brand;

@Named
public class BrandService {
	private  BrandDao brandDao;
	
	@Inject
	public BrandService(BrandDao brandDao){
		this.brandDao=brandDao;
	}
	
	 public List<Brand> Brand(int limit, int skip, String sort, String order,String categoryId) {
		 List<Brand> brand=brandDao.Brand(limit, skip, sort, order,categoryId);
		 return brand;
	 }
}
