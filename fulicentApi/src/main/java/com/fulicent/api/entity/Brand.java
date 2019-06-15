package com.fulicent.api.entity;
import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fulicent.common.entity.DataInfo;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"parentId"})
public class Brand  implements DataInfo{
	private int id;
	private String name;
	private String images;
	private int categoryId;
	
	private int parentId;
	private List<Brand> brandList;
}
