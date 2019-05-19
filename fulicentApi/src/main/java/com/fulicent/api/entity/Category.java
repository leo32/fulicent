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
	private String links;
	private String content;
	private String image;
	private int price;
	private int discount;
	private int commission;
	private int anticipation;
	private int status;
	private int type;
}
