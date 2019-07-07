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
	private String numIID;
	private String links;
	private String content;
	private String image;
	private double couponPrice;
	private String coupon;
	private double reservePrice;
	private String anticipation;
	private int status;
	private int type;
	private String expire;
	private String categoryId;
	private String brand;
	private int couponRemainCount;
	private double sale;
	private String smallImages;
	private int recommend;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	private Date createTime;

}
