package com.fulicent.api.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulicent.api.entity.CategoryInfo;
import com.fulicent.api.entity.Category;
import com.fulicent.api.service.CategoryService;
import com.fulicent.common.entity.ApiResponseBody;
import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.entity.MessageInfo;

@RestController
@RequestMapping("/category")
public class CategoryController {
	private CategoryService CategoryService;
	
	@Inject
	public CategoryController(CategoryService CategoryService){
		this.CategoryService=CategoryService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ApiResponseBody> Category(@RequestParam(name = "limit", defaultValue = "100") int limit,
            @RequestParam(name = "skip", defaultValue = "0") int skip,
            @RequestParam(value = "sort", required = false, defaultValue = "updated") String sort,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order){
		List<Category> Category=CategoryService.Category(limit, skip, sort, order);
		return new ResponseEntity<>(ApiResponseBody.builder()
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.data(new CategoryInfo(Category))
				.build(),
				HttpStatus.OK
				);
		
	}
}
