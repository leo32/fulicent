package com.fulicent.api.dao;

import com.fulicent.api.entity.Products;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductsDao {
	 @Select("<script> "+
	    "SELECT " +
	    "t.* " +
	    "FROM " +
	    " fulicent.products t " +
	    "WHERE " +
	    " t.Status=1 and t.Type=0 " +
	    "<if test='categoryId!=\"\" and categoryId!=null'>" +
	    " and t.CategoryId= ${categoryId} " +
	    "</if>  " +	    
	    "<if test='brand!=\"\" and brand!=null'>" +
	    " and t.Brand= ${brand} " +
	    "</if>  " +	    
	    "<if test='recommend!=\"\" and recommend!=null'>" +
	    " and t.Recommend> ${recommend} " +
	    "</if>  " +		            
	    "<if test='sort == \"Name\"'>  " +
	    " ORDER BY t.Name ${order} " +
	    "</if>  " +
	    "<if test='sort == \"CreateTime\"'>  " +
	    " ORDER BY t.CreateTime  ${order} " +
	    "</if>  " +	   
	    "<if test='sort == \"Recommend\"'>  " +
	    " ORDER BY t.Recommend  ${order} " +
	    "</if>  " +
	    "<if test ='limit gt 0'>" +
	    "LIMIT #{limit} OFFSET #{skip}</if>" +
	    "</script>")
	List<Products> Products(@Param("limit") int limit, @Param("skip") int skip, @Param("sort") String sort, @Param("order") String order, @Param("categoryId") String categoryId, @Param("recommend") String recommend, @Param("brand") String brand);

	 @Select("<script>SELECT A.Name,Links,Content,A.Image,Price,Discount,Commission,Anticipation,A.Id,Expire,Sale,DiscountNum,B.Name as CategoryId FROM fulicent.products A, fulicent.category B where A.CategoryId=B.Id and A.id=${id};"
	 		+ "insert into fulicent.topproducts(productId,Count) value(${id},1) on duplicate key update Count=Count+1;</script>")
	 Products Product(@Param("id") int id);
	 
	 @Select("<script>select t.* from fulicent.topproducts A, fulicent.products t where t.Type=0 and t.Status=1 and A.ProductId=t.Id"+
			    "<if test='categoryId!=\"\" and categoryId!=null'>" +
			    " and t.CategoryId= ${categoryId} " +
			    "</if>  " +	
			    " order by A.count desc"+
			    "<if test ='limit gt 0'>" +
			    "LIMIT #{limit} OFFSET #{skip}</if>" +
			    "</script>")
	 List<Products> TopProducts(@Param("limit") int limit, @Param("skip") int skip, @Param("categoryId") String categoryId);

	 @Select("<script> "+
			    "SELECT " +
			    "t.* " +
			    "FROM " +
			    " fulicent.products t " +
			    "WHERE " +
			    "t.Type in(1,2,3)"+ 
			    "<if test ='limit gt 0'>" +
			    "LIMIT #{limit} OFFSET #{skip}</if>" +
			    "</script>")
	 List<Products> AdProducts(@Param("limit") int limit, @Param("skip") int skip);
}
