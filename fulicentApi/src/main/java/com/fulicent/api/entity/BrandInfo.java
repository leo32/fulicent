package com.fulicent.api.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.fulicent.common.entity.DataInfo;

@Data
public class BrandInfo implements DataInfo  {
	private List<Brand> brandsList;
	
	public BrandInfo(List<Brand> brands){
		brandsList =new ArrayList<>();
		int j=-1;
		for(int i=0;i<brands.size();i++){
			Brand brand=new Brand();
			brand.setName(brands.get(i).getName());
			brand.setImages(brands.get(i).getImages());
			if(brands.get(i).getParentId()==0){
				brand.setId(brands.get(i).getId());
				brand.setBrandList(new ArrayList<>());
				brandsList.add(brand);
				j++;
			}else{
				brand.setId(brands.get(i).getParentId());
				brandsList.get(j).getBrandList().add(brand);
			}
		}
	}
}
