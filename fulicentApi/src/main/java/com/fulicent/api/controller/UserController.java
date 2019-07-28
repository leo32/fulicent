package com.fulicent.api.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulicent.api.entity.User;
import com.fulicent.api.entity.UserInfo;
import com.fulicent.api.service.UserService;
import com.fulicent.common.entity.ApiResponseBody;
import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.entity.MessageInfo;
import com.fulicent.common.entity.Pagination;
import com.fulicent.common.utils.JsonUtils;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private UserService userService;
	
	@Inject
	public UserController(UserService userService){
		this.userService=userService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ApiResponseBody> users(@RequestParam(name = "limit", defaultValue = "1000") int limit,
            @RequestParam(name = "skip", defaultValue = "0") int skip,
            @RequestParam(name = "sort", required = false, defaultValue = "Id") String sort,
            @RequestParam(name = "order", required = false, defaultValue = "desc") String order,
            @RequestParam(name = "userName", required = false, defaultValue = "") String userName,
            @RequestParam(name = "email", required = false, defaultValue = "") String email,
            @RequestParam(name = "password", required = false, defaultValue = "") String password,
            @RequestParam(name = "token", required = false, defaultValue = "") String token
			){
		Pagination pagination = new Pagination(skip, limit, 0);
		List<User> users=userService.Users(limit, skip, sort, order, userName, email, password, token,pagination);
		return new ResponseEntity<>(ApiResponseBody.builder()
				.pagination(pagination)
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.data(new UserInfo(users))
				.build(),
				HttpStatus.OK
				);
		
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<ApiResponseBody> SaveUser(@RequestBody String body){
		User user=JsonUtils.fromJsonString(body, User.class);
		user.setStatus(2);
		int result=userService.Save(user);
	   return new ResponseEntity<>(
			   ApiResponseBody.builder()
	                    .status(new MessageInfo(ApiResponseStatus.RESOURCE_CREATE_SUCCESS))
	                    .build(),
	            HttpStatus.OK);
	}


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ApiResponseBody> PutUser(@PathVariable("id") int id,@RequestBody String body){
		User user=JsonUtils.fromJsonString(body, User.class);
		user.setId(id);
		userService.Update(user);
	       return new ResponseEntity<>(
	    		   ApiResponseBody.builder()
	                        .status(new MessageInfo(ApiResponseStatus.RESOURCE_UPDATE_SUCCESS))
	                        .build(),
	                HttpStatus.OK);
	}
}
