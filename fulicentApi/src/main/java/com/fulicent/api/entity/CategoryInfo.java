package com.fulicent.api.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.fulicent.common.entity.DataInfo;

@Data
@AllArgsConstructor
public class CategoryInfo implements DataInfo {
	private List<Category> CategoryList;
	
	public CategoryInfo(Category Category){
		this.CategoryList=new ArrayList<>();
		CategoryList.add(Category);
	}
}

