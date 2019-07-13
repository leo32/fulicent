package com.fulicent.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.fulicent.api.entity.Category;
import com.fulicent.api.entity.Products;

@Mapper
public interface CategoryDao {
	 @Select("<script> "+
	            "SELECT " +
	            " * " +
	            "FROM " +
	            " fulicent.category t " +
	            "WHERE " +
	            " t.Status=1 " +
	            "<if test='sort == \"Name\"'>  " +
	            " ORDER BY t.Name ${order} " +
	            "</if>  " +
	            "<if test='sort == \"CreateTime\"'>  " +
	            " ORDER BY t.CreateTime  ${order} " +
	            "</if>  " +
	            "<if test ='limit gt 0'>" +
	            "LIMIT #{limit} OFFSET #{skip}</if>" +
	            "</script>")
	    List<Category> Category(@Param("limit") int limit, @Param("skip") int skip, @Param("sort") String sort, @Param("order") String order);
	 
		@Insert("INSERT INTO "+
		"fulicent.category (`Name`, `Image`, `Status`, `ParentId`, `TaobaoCat`)"+
		"Values(#{name},#{image},#{status},#{parentId},#{taobaoCat})")
	    @SelectKey(statement = "SELECT LAST_INSERT_ID() AS id", keyProperty = "id", before = false, resultType = int.class)
	    int SaveCategory(Category category);
}
