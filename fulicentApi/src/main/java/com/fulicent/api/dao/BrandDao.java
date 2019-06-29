package com.fulicent.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fulicent.api.entity.Brand;

@Mapper
public interface BrandDao {
	 @Select("<script> "+
	            "Select Id,0 as parentId,Name,Image as Images, '1' as num from fulicent.category t<if test='categoryId!=\"\" and categoryId!=null'> where t.Id= ${categoryId}</if> union SELECT CategoryId as Id, Id as parentId ,Name,Images,'2' as num FROM fulicent.brand t<if test='categoryId!=\"\" and categoryId!=null'> where t.CategoryId= ${categoryId}</if>" +	
			    "  order by Id asc, num asc"+
	            "<if test ='limit gt 0'>" +
	            "LIMIT #{limit} OFFSET #{skip}</if>" +
	            "</script>")
	    List<Brand> Brand(@Param("limit") int limit, @Param("skip") int skip, @Param("sort") String sort, @Param("order") String order, @Param("categoryId") String categoryId);

}
