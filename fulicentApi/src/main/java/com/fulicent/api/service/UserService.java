package com.fulicent.api.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.annotations.Param;

import com.fulicent.api.dao.UserDao;
import com.fulicent.api.entity.User;
import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.entity.Pagination;
import com.fulicent.common.exception.ApiException;

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
		 if(users==null||users.size()==0) throw new ApiException(ApiResponseStatus.EMAILORPASSWORD_CANNOT_MATCH);
		 for(User user: users){
			 user.setPassword("");
			 user.setToken("");
		 }
		 pagination.setCount(this.userDao.Count(userName, email, password, token));
		 return users;
	 }
	 
	 public int Save(User user){
		 int status=0;
		 try{
			 status=this.userDao.Save(user);
		 }
		 catch(Exception err){
			 throw new ApiException(ApiResponseStatus.EMAIL_CANNOT_DUPLICATE);
		 }
		 return status;
	 }
	 
	 public int Update(User user){
		 return this.userDao.Update(user);
	 }
}
