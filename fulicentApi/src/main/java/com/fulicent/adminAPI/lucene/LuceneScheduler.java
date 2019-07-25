package com.fulicent.adminAPI.lucene;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulicent.api.service.ProductsService;
import com.fulicent.common.entity.ApiResponseBody;
import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.entity.DocInfo;
import com.fulicent.common.entity.LuceneDoc;
import com.fulicent.common.entity.MessageInfo;
import com.fulicent.common.exception.ApiException;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@RequestMapping("/admin/lucene")
public class LuceneScheduler {
	
	private ProductsService productsService;
	private String indexPath;
	
	@Inject
	public LuceneScheduler(ProductsService productsService,
			@Value(value="${lucene.path}") String indexPath){
		this.productsService=productsService;
		this.indexPath=indexPath;
	}
	
	@Scheduled(cron = "59 59 02 * * *")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public ResponseEntity<ApiResponseBody> CreateLucene(){
		IndexWriter indexWriter = null;
		try{
            Path paths=Paths.get(indexPath);
			if (!Files.exists(paths)) {
				Files.createDirectories(paths);
			}
            List<LuceneDoc> luceneDocs=productsService.GetLuceneDoc();
			FSDirectory fsDirectory = FSDirectory.open(paths);
			Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig  = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);
            indexWriter.deleteAll();
            for(LuceneDoc doc:luceneDocs){
            	Document document = doc.toDocument();
            	indexWriter.addDocument(document);
            }
            indexWriter.close();
		}
		catch(Exception e){
			throw new ApiException(ApiResponseStatus.RESOURCE_NOT_FOUND);
		}
		finally{
			if(indexWriter!=null){
				try{
					indexWriter.close();
				}
				catch(Exception err){
					throw new ApiException(ApiResponseStatus.RESOURCE_NOT_FOUND);
				}
			}
		}
		return new ResponseEntity<>(ApiResponseBody.builder()
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.build(),
				HttpStatus.OK
				);
	}
	
	
	
	
	
}
