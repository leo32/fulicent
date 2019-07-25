package com.fulicent.common.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.fulicent.api.entity.Category;
import com.fulicent.api.entity.CategoryInfo;

@Data
@AllArgsConstructor
public class DocInfo  implements DataInfo  {
	private List<LuceneDoc> ProductList;
	
	public DocInfo(LuceneDoc doc){
		this.ProductList=new ArrayList<>();
		ProductList.add(doc);
	}
}
