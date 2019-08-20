package com.fulicent.api.entity;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.fulicent.common.entity.DataInfo;
@Data
public class AdProductsInfo implements DataInfo {
	private List<Products> ad1ProductList =new ArrayList<>();
	private List<Products> ad2ProductList =new ArrayList<>();
	private List<Products> ad3ProductList =new ArrayList<>();
	
	public AdProductsInfo(List<Products> products){
		for(int i=0;i<products.size();i++){
			if(products.get(i).getType()==1){
				ad1ProductList.add(products.get(i));
			}
			if(products.get(i).getType()==2){
				ad2ProductList.add(products.get(i));
			}
			if(products.get(i).getType()==3){
				ad3ProductList.add(products.get(i));
			}
		}
	}
}
