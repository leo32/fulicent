package com.fulicent.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fulicent.common.entity.DataInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo  implements DataInfo {
	private List<User> UserList;
	
	public UserInfo(User user){
		this.UserList=new ArrayList<>();
		UserList.add(user);
	}
}
