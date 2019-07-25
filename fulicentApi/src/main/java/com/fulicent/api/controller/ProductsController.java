package com.fulicent.api.controller;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fulicent.api.entity.AdProductsInfo;
import com.fulicent.api.entity.ProductsInfo;
import com.fulicent.api.entity.Products;
import com.fulicent.api.service.ProductsService;
import com.fulicent.common.entity.ApiResponseBody;
import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.entity.DocInfo;
import com.fulicent.common.entity.LuceneDoc;
import com.fulicent.common.entity.MessageInfo;
import com.fulicent.common.entity.Pagination;
import com.fulicent.common.exception.ApiException;

import javax.inject.Inject;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductsController {
	private ProductsService productsService;
	private String indexPath;
	
	@Inject
	public ProductsController(ProductsService productsService,@Value(value="${lucene.path}") String indexPath){
		this.productsService=productsService;
		this.indexPath=indexPath;
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
            @RequestParam(name = "tags", required = false, defaultValue = "") String tags,
            @RequestParam(name = "ids", required = false, defaultValue = "") String ids
			){
		Pagination pagination = new Pagination(skip, limit, 0);
		List<Products> products=productsService.Products(limit, skip, sort, order,categoryId,type,recommend,brand,tags,ids,pagination);
		return new ResponseEntity<>(ApiResponseBody.builder()
				.pagination(pagination)
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
		Pagination pagination = new Pagination(skip, limit, 0);
		List<Products> product=productsService.MyProducts(limit, skip,ids,pagination);
		return new ResponseEntity<>(ApiResponseBody.builder()
				.pagination(pagination)
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.data(new ProductsInfo(product))
				.build(),
				HttpStatus.OK
				);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<ApiResponseBody> searchProducts(
			@RequestParam(name = "limit", defaultValue = "12") int limit,
            @RequestParam(name = "skip", defaultValue = "0") int skip,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword
			){
		DirectoryReader  directoryReader=null;
		try{
		   int end = skip + limit;
			Pagination pagination = new Pagination(skip, limit, 0);
			List<LuceneDoc> luceneDocs=new ArrayList<LuceneDoc>();
			directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
			IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
			String[] fields = {"name", "tag"};
			Analyzer analyzer = new StandardAnalyzer();
			MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);
			Query query = queryParser.parse(keyword);
			Sort sort = new Sort(new SortField("sortid", Type.LONG, true));
			TopDocs topDocs = indexSearcher.search(query, end,sort);
			
			pagination.setCount((int)topDocs.totalHits);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if(end>pagination.getCount()){
				end=pagination.getCount();
			}
			for (int i=skip;i<end;i++) {
				int id = scoreDocs[i].doc;
				Document document = indexSearcher.doc(id);
				luceneDocs.add(LuceneDoc.parseDoc(document));
			}
			
			return new ResponseEntity<>(ApiResponseBody.builder()
					.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
					.pagination(pagination)
					.data(new DocInfo(luceneDocs))
					.build(),
					HttpStatus.OK
					);
		}
		catch(Exception err){
			throw new ApiException(ApiResponseStatus.RESOURCE_NOT_FOUND);
		}
		finally{
			if(directoryReader!=null){
				try{
					directoryReader.close();
				}
				catch(Exception err){
					throw new ApiException(ApiResponseStatus.RESOURCE_NOT_FOUND);
				}
			}
		}
	}
}
