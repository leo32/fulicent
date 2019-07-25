package com.fulicent.api.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.annotations.Param;

import com.fulicent.api.dao.UserDao;
import com.fulicent.api.entity.User;
import com.fulicent.common.entity.Pagination;

import java.util.Arrays;
import java.util.List;

@Named
public class UserService {
	private  UserDao userDao;
	
	@Inject
	public UserService(UserDao userDao){
		this.userDao=userDao;
	}
	
	 public List<User> Users(int limit, int skip, String sort, String order, String userName,String email,String password, String token,Pagination pagination) {
		 List<User> users=this.userDao.Users(limit, skip, sort, order, userName, email, password, token);
		 for(User user: users){
			 user.setPassword("");
			 user.setToken("");
		 }
		 pagination.setCount(this.userDao.Count(userName, email, password, token));
		 return users;
	 }
	 
	 public int Save(User user){
		 return this.userDao.Save(user);
	 }
	 
	 public int Update(User user){
		 return this.userDao.Update(user);
	 }
}
