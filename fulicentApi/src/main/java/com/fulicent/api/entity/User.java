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
public class User  implements DataInfo{
	private int id;
	private String userName;
	private String email;
	private String password;
	private String token;
	private int status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	private Date createTime;
}
