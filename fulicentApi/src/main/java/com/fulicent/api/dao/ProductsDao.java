package com.fulicent.api.dao;

import com.fulicent.api.entity.Products;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductsDao {
	 @Select("<script> "+
	            "SELECT " +
	            " * " +
	            "FROM " +
	            " fulicent.products t " +
	            "WHERE " +
	            " t.Status=1 " +
	            "<if test='type!=\"\" and type!=null'>" +
	            " and t.Type= ${type} " +
	            "</if>  " +	            
	            "<if test='sort == \"Name\"'>  " +
	            " ORDER BY t.Name ${order} " +
	            "</if>  " +
	            "<if test='sort == \"CreateTime\"'>  " +
	            " ORDER BY t.CreateTime  ${order} " +
	            "</if>  " +
	            "<if test ='limit gt -1'>" +
	            "LIMIT #{limit} OFFSET #{skip}</if>" +
	            "</script>")
	    List<Products> Products(@Param("limit") int limit, @Param("skip") int skip, @Param("sort") String sort, @Param("order") String order, @Param("type") String type);
}
