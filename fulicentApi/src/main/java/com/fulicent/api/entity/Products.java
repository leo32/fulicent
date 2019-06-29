package com.fulicent.api.entity;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fulicent.common.entity.DataInfo;
import com.fulicent.common.utils.JsonUtils;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Products implements DataInfo{
	private int id;
	private String name;
	private String links;
	private String content;
	private String image;
	private int price;
	private String discount;
	private int commission;
	private String anticipation;
	private int status;
	private int type;
	private String expire;
	private String categoryId;
	private String brand;
	private int discountNum;
	private int sale;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	private Date createTime;

}
