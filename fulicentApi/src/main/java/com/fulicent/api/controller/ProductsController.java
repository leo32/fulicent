package com.fulicent.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fulicent.api.entity.AdProductsInfo;
import com.fulicent.api.entity.ProductsInfo;
import com.fulicent.api.entity.Products;
import com.fulicent.api.service.ProductsService;
import com.fulicent.common.entity.ApiResponseBody;
import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.entity.MessageInfo;

import javax.inject.Inject;

import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductsController {
	private ProductsService productsService;
	
	@Inject
	public ProductsController(ProductsService productsService){
		this.productsService=productsService;
	}
	
	//@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ApiResponseBody> products(@RequestParam(name = "limit", defaultValue = "1000") int limit,
            @RequestParam(name = "skip", defaultValue = "0") int skip,
            @RequestParam(name = "sort", required = false, defaultValue = "CreateTime") String sort,
            @RequestParam(name = "order", required = false, defaultValue = "desc") String order,
            @RequestParam(name = "categoryId", required = false, defaultValue = "") String categoryId,
            @RequestParam(name = "type", required = false, defaultValue = "") String type,
            @RequestParam(name = "recommend", required = false, defaultValue = "") String recommend,
            @RequestParam(name = "brand", required = false, defaultValue = "") String brand,
            @RequestParam(name = "ids", required = false, defaultValue = "") String ids
			){
		List<Products> products=productsService.Products(limit, skip, sort, order,categoryId,type,recommend,brand,ids);
		return new ResponseEntity<>(ApiResponseBody.builder()
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.data(new ProductsInfo(products))
				.build(),
				HttpStatus.OK
				);
		
	}
	
	//@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponseBody> product(@PathVariable(name = "id") int id
			){
		Products product=productsService.Product(id);
		return new ResponseEntity<>(ApiResponseBody.builder()
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.data(product)
				.build(),
				HttpStatus.OK
				);
	}
	
	//@CrossOrigin
	@RequestMapping(value = "/ad", method = RequestMethod.GET)
	public ResponseEntity<ApiResponseBody> Adproduct(@RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "skip", defaultValue = "0") int skip){
		List<Products> product=productsService.AdProducts(limit, skip);
		return new ResponseEntity<>(ApiResponseBody.builder()
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.data(new AdProductsInfo(product))
				.build(),
				HttpStatus.OK
				);
	}
	
	@RequestMapping(value = "/my/{ids}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponseBody> MyProducts(@RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "skip", defaultValue = "0") int skip,            
            @PathVariable(name = "ids", required = false) String ids){
		String[] idList={};
		if(!ids.isEmpty()){
			idList=ids.split(",");
		}
		List<Products> product=productsService.MyProducts(limit, skip,idList);
		return new ResponseEntity<>(ApiResponseBody.builder()
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.data(new ProductsInfo(product))
				.build(),
				HttpStatus.OK
				);
	}
}
