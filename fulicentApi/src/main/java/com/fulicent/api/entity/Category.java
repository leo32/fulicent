package com.fulicent.api.entity;

import java.util.Date;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fulicent.common.entity.DataInfo;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category  implements DataInfo{
	private int id;
	private String name;
	private String image;
	private int status;
	private int parentId;
	private String taobaoCat;
}
