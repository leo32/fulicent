package com.fulicent.api.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.fulicent.common.entity.DataInfo;
@Data
@AllArgsConstructor
public class ProductsInfo implements DataInfo {
	private List<Products> ProductList;
	
	public ProductsInfo(Products products){
		this.ProductList=new ArrayList<>();
		ProductList.add(products);
	}
}
