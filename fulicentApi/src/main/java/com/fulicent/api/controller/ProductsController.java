package com.fulicent.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fulicent.api.entity.ProductsInfo;
import com.fulicent.api.entity.Products;
import com.fulicent.api.service.ProductsService;
import com.fulicent.common.entity.ApiResponseBody;
import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.entity.MessageInfo;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductsController {
	private ProductsService productsService;
	
	@Inject
	public ProductsController(ProductsService productsService){
		this.productsService=productsService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ApiResponseBody> products(@RequestParam(name = "limit", defaultValue = "100") int limit,
            @RequestParam(name = "skip", defaultValue = "0") int skip,
            @RequestParam(value = "sort", required = false, defaultValue = "updated") String sort,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order){
		List<Products> products=productsService.Products(limit, skip, sort, order);
		return new ResponseEntity<>(ApiResponseBody.builder()
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.data(new ProductsInfo(products))
				.build(),
				HttpStatus.OK
				);
		
	}
}
